package main.java.util;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import main.java.model.Vocabulaire;
import main.java.model.WebResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class UrlReader {

    public static Vocabulaire readURL(String motif, Integer tailleMin, Integer presence) throws IOException {


        //String url = "https://fr.wikipedia.org/wiki/" + motif;
        String url = "http://www.cnrtl.fr/definition/" + motif;

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String content = response.getContentAsString();
        String[] lignes_base = content.split("<span class=\"tlf_cdefinition\">");
        String[] lignes = new String[lignes_base.length -2];

        for (int i = 1; i < lignes_base.length -1; i++){
            lignes[i - 1] = lignes_base[i];
        }

        WebResult webResult = new WebResult(Arrays.stream(lignes).collect(Collectors.joining("\n")));
        //System.out.println(webResult.getString());
        //System.out.println("----------------");
        String phrases = Statistiques.countWords(motif, webResult.getString(), tailleMin, presence).entrySet().stream()
                .map(a -> a.getKey() + " : " + a.getValue().stream().collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
        //System.out.println(new WebResult(content.split("<span>"+ motif.toUpperCase() + ".*</span>")[1].split("<b>PRONONC. ET ORTH.")[0]).getString());
        return new Vocabulaire(webResult.getString(), phrases);
    }
}
