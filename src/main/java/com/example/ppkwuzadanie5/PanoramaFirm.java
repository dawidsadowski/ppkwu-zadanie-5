package com.example.ppkwuzadanie5;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.Address;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PanoramaFirm {

    static String html;

    public static String getResults(String query) throws IOException {
        html = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>VCard dla Panoramy Firm</title>" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "</head>\n" +
                "<body><div>Wyniki wyszukiwania dla frazy <b>" + query + "</b>:</div>";

        Document doc = Jsoup.connect("https://panoramafirm.pl/" + query).get();

        Elements elements = doc.select("script[type=\"application/ld+json\"]");
        elements.remove(elements.last());

        String name = "";
        String image = "";
        String telephone = "";
        String email = "";
        String website = "";

        String street = "";
        String city = "";
        String postalCode = "";

        for(Element element : elements) {
            String jsonString = element.html();
            JSONObject jsonObject = new JSONObject(jsonString);

            try {name = jsonObject.getString("name"); }
            catch(Exception ignored) {}
            try {image = jsonObject.getString("image"); }
            catch(Exception ignored) {}
            try {telephone = jsonObject.getString("telephone"); }
            catch(Exception ignored) {}
            try {email = jsonObject.getString("email"); }
            catch(Exception ignored) {}
            try {website = jsonObject.getString("sameAs"); }
            catch(Exception ignored) {}

            try {
                JSONObject address = jsonObject.getJSONObject("address");

                try {street = address.getString("streetAddress"); }
                catch(Exception ignored) {}
                try {city = address.getString("addressLocality"); }
                catch(Exception ignored) {}
                try {postalCode = address.getString("postalCode"); }
                catch(Exception ignored) {}
            } catch(Exception ignored) {}

            String params = String.format("name=%s&telephone=%s&email=%s&website=%s&street=%s&postalCode=%s&city=%s",
                    name, telephone, email, website, street, postalCode, city).replaceAll(" ", "%20");

            html += String.format("<div>\n" +
                    "    <p><b>%s</b></p>\n" +
                    "    <p><img src=\"%s\" alt=\"logo firmy\"></img>\n" +
                    "    <p>Telefon: %s</p>\n" +
                    "    <p>Adres e-mail: %s</p>\n" +
                    "    <p>Strona internetowa: %s</p>\n" +
                    "  \n" +
                    "    <p>%s</p>\n" +
                    "    <p>%s</p>\n" +
                    "    <p>%s</p>\n" +
                    "    <p>" +
                    "<a href=/api/vcard/?%s>" +
                    "<button>Generuj vCard</button></a></p>\n" +
                    "  </div><br/><br/>",
                    name, image, telephone, email, website, street, postalCode, city, params);
        }

        html += "</body></html>";
        return html;
    }

    public static String getVcard(String name, String telephone, String email, String website, String street, String postalCode, String city) {
        VCard vcard = new VCard();

        Address address = new Address();
        address.setPostalCode(postalCode);
        address.setStreetAddress(street);
        address.setRegion(city);

        vcard.setFormattedName(name);
        vcard.addTelephoneNumber(telephone);
        vcard.addEmail(email);
        vcard.addUrl(website);
        vcard.addAddress(address);

        return Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
    }
}
