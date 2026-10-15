import org.json.JSONArray;
import org.json.JSONObject;



void main() throws IOException {
    Scanner sc = new Scanner(System.in);

    Path path = Path.of("data", "vyprava.json");

    String data = Files.readString(path);
    JSONObject root = new JSONObject(data);

    JSONArray koberce = new JSONArray(root.get("koberce").toString());
    JSONArray opicky = new JSONArray(root.get("opicky").toString());
    int pocetOpicek = opicky.length();
    System.out.println(pocetOpicek);

    for (int i = 0; i < koberce.length(); i++) {
        JSONObject koberec = koberce.getJSONObject(i);
        if ((boolean) koberec.get("aktivni")){
            System.out.println("ID: " + koberec.get("id") + " | Název: " + koberec.get("nazev") + " | Nosnost: " + koberec.get("nosnost") + " | Je dostupný: ANO");
        } else {
            System.out.println("ID: " + koberec.get("id") + " | Název: " + koberec.get("nazev") + " | Nosnost: " + koberec.get("nosnost") + " | Je dostupný: NE");
        }
    }

    System.out.println("");

    int hmotnostVypravy = 0;
    boolean banany = true;
    while (banany){
            System.out.print("Počet banánů: ");
            int pocetBann = sc.nextInt();
        if (pocetBann >= 0 && pocetBann <= 10000) {
            hmotnostVypravy = pocetBann + pocetOpicek * 15;
            System.out.println("Celková hmotnost výpravy je: " + hmotnostVypravy);
            banany = false;
        } else {
            System.out.println("Takový počet banánů není možný! Napiš to znovu!!!!");
        }
    }

    System.out.println("");

    JSONArray vhodne =  new JSONArray();
    for (int i = 0; i < koberce.length(); i++) {
        JSONObject koberec = koberce.getJSONObject(i);
            if ((boolean) koberec.get("aktivni") && koberec.get("vybava").toString().contains("kompas") && koberec.getInt("nosnost") >= hmotnostVypravy) {
                System.out.println("Název:" + koberec.get("nazev")+" | Nosnost:"+ koberec.get("nosnost"));
                vhodne.put(koberec);
            }

    }

    System.out.println("");

    int nejlepsiNosnost = vhodne.getJSONObject(0).getInt("nosnost");
    for (int i = 0; i < vhodne.length(); i++) {
        JSONObject koberec = vhodne.getJSONObject(i);
        if (nejlepsiNosnost > koberec.getInt("nosnost")) {
            nejlepsiNosnost = koberec.getInt("nosnost");
        }
    }

    for (int i = 0; i < vhodne.length(); i++) {
        JSONObject koberec = vhodne.getJSONObject(i);
        if (nejlepsiNosnost == koberec.getInt("nosnost")) {
            System.out.println("Název: "+ koberec.get("nazev"));
        }
    }
    System.out.println("švihák před změnou: "+koberce.getJSONObject(0).get("aktivni"));
    koberce.getJSONObject(0).put("aktivni",false);
    System.out.println("švihák po změně: "+koberce.getJSONObject(0).get("aktivni"));
}


