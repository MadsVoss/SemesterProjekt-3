import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CGITider {
    public static void main(String[] args) {


        System.out.println("Content-Type: text/html");
        System.out.println();
        System.out.println("<!DOCTYPE html>\n<html>");
        System.out.println("<title>SundtekSygehus - Mine Tider</title>");
        System.out.println("<meta charset=\"Windows-1252\">");
        System.out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
        System.out.println("<link rel=\"stylesheet\" href=\"/Aftaler.css\">\n");
        System.out.println("<body class=\"w3-light-grey w3-content\" style=\"max-width:1600px\">");

        String data = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            data = in.readLine();

        } catch (IOException ioe) {
            System.out.println("<P>IOException reading POST data: " + ioe + "</P>");
        } finally{
            //System.out.println("Finally");
            //System.out.println("Læst data " + data);

        }

        //System.out.println("Læst data " + data);

        Map<String, String> map = new HashMap<String, String>();
        if(data!=null && data!="") {


        StringTokenizer t = new StringTokenizer(data);

        String felt;
        String navn;
        String vaerdi;

        while (t.hasMoreTokens()) {
            felt = t.nextToken();
            if (felt != null) {

                StringTokenizer tt = new StringTokenizer(felt, "=\n\r");
                navn = tt.nextToken();
                vaerdi = null;
                if (navn != null) {
                    //System.out.print("navn =" +navn);
                    vaerdi = tt.nextToken();
                    if (vaerdi != null) {
                        //System.out.print("vaerdi =" +vaerdi);

                        vaerdi = vaerdi.replaceAll("%C3%A6", "æ");
                        vaerdi = vaerdi.replaceAll("%C3%B8", "ø");
                        vaerdi = vaerdi.replaceAll("%C3%A5", "å");
                        vaerdi = vaerdi.replaceAll("%C3%86", "Æ");
                        vaerdi = vaerdi.replaceAll("%C3%98", "Ø");
                        vaerdi = vaerdi.replaceAll("%C3%85", "Å");
                        map.put(navn, vaerdi);
                    }

                }

            }
        }
        }
        //Slet en aftale hvis vi kommer hertil
        if(map.containsKey("sletAftale")) {
        int aftaleId = Integer.parseInt(map.get("sletAftale"));
        Funktioner.sletAftale(aftaleId);


        }

            System.out.println("<nav class=\"w3-sidebar w3-collapse w3-white w3-animate-left\" style=\"z-index:3;width:300px;\" id=\"mySidebar\"><br>\n");
            System.out.println("    <div class=\"w3-container\">\n");
            System.out.println("        <a href=\"#\" onclick=\"w3_close()\" class=\"w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey\" title=\"close menu\">\n");
            System.out.println("            <i class=\"fa fa-remove\"></i>\n");
            System.out.println("        </a>\n");
            System.out.println("        <img src=\"/HARALD.png\" style=\"width:45%\"/><br><br>\n");
            System.out.println("        <h4><b>Harald Gemsmerte</b></h4>\n");
            System.out.println("        <p class=\"w3-text-grey\">040769-0787</p>\n");
            System.out.println("    </div>\n");
            System.out.println("    <div class=\"w3-bar-block\">\n");
            System.out.println("        <a href=\"/Journal.html\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button w3-padding\"><i class=\"fa fa-th-large fa-fw w3-margin-right\"></i>Journal</a>\n");
            System.out.println("        <a href=\"/Tidsbestilling.html\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button w3-padding\"><i class=\"fa fa-user fa-fw w3-margin-right\"></i>Tidsbestilling</a>\n");
            System.out.println("        <a href=\"/cgi-bin/CGITider\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button w3-padding\"><i class=\"fa fa-user fa-fw w3-margin-right\"></i>Mine Tider</a>\n");
            System.out.println("    </div>\n");
            System.out.println("</nav>\n");
            System.out.println("<div class=\"w3-overlay w3-hide-large w3-animate-opacity\" onclick=\"w3_close()\" style=\"cursor:pointer\" title=\"close side menu\" id=\"myOverlay\"></div>\n");
            System.out.println("<div class=\"w3-main\" style=\"margin-left:300px\">\n");
            System.out.println("    <header id=\"portfolio\">\n");
            System.out.println("        <a href=\"#\"><img src=\"/w3images/avatar_g2.jpg\" style=\"width:65px;\" class=\"w3-circle w3-right w3-margin w3-hide-large w3-hover-opacity\"></a>\n");
            System.out.println("        <span class=\"w3-button w3-hide-large w3-xxlarge w3-hover-text-grey\" onclick=\"w3_open()\"><i class=\"fa fa-bars\"></i></span>\n");
            System.out.println("        <div class=\"w3-container\">\n");
            System.out.println("            <h1><b>Mine tider</b></h1>\n");
            System.out.println("        </div>\n");
            System.out.println("    </header>\n");
            System.out.println("    <div class=\"w3-container w3-padding-large\" style=\"margin-bottom:32px\">\n");
            System.out.println("<form method=post action=\"/cgi-bin/CGITider\"> \n");
            System.out.println("        <table>\n");
            System.out.println("            <tr>\n");
            System.out.println("                <th>Dato</th>\n");
            System.out.println("                <th>Varighed</th>\n");
            System.out.println("                <th>&Aring;rsag</th>\n");
            System.out.println("                <th>Hospital</th>\n");
            System.out.println("                <th>Lokale</th>\n");
            System.out.println("                <th>Slet</th>\n");
            System.out.println("            </tr>\n");

            Funktioner.listAftaler();


            System.out.println("        </form>\n");
            System.out.println("    </div>\n");
            System.out.println("</body>\n");
            System.out.println("</html>\n");



    }
}
