package pl.kitowcy.louis.proposition;

import java.util.ArrayList;
import java.util.List;

import pl.kitowcy.louis.R;

/**
 * Created by Patryk Mieczkowski on 29.10.2016
 */

public class PropositionItemsProvider {

    public static List<PropItem> getItems() {
        List<PropItem> propItemList = new ArrayList<>();
        propItemList.add(getBeksinski());
        propItemList.add(getJazzFestiwal());
        propItemList.add(getRolki());
        propItemList.add(getMovie());

        return propItemList;
    }

    private static PropItem getBeksinski() {
        return new PropItem("Zdzisław Beksiński Gallery",
                "Obrazy, rysunki i fotografie - łącznie 250 prac z prywatnej kolekcji. " +
                        "Beksiński należy do wąskiego grona polskich artystów, których twórczość jest " +
                        "tak dobrze rozpoznawalna zarówno w Polsce, jak i w Europie. Rodzące wiele emocji i kontrowersji " +
                        "dzieła Artysty nikogo nie pozostawiają obojętnym.",
                "10.00 - 22.00",
                "NCK\nal. Jana Pawła II 232",
                R.drawable.beksinski);
    }

    private static PropItem getJazzFestiwal() {
        return new PropItem("11th Krakow Jazz Autumn",
                "Jazz concert features France’s duo of double bassist Joelle Leandre and trumpeter Jean-Luc Cappozzo.",
                "20.00",
                "Alchemia\nul. Estery 5",
                R.drawable.jazz);
    }

    private static PropItem getMovie() {
        return new PropItem("Cinema City - Doctor Strange",
                "Jazz concert features France’s duo of double bassist Joelle Leandre and trumpeter Jean-Luc Cappozzo.",
                "21.15",
                "Cinema City Bonarka",
                R.drawable.doctorstrange);
    }

    private static PropItem getRolki() {
        return new PropItem("Night rollerblading",
                "Best way to spend active night at beautiful city",
                "",
                "",
                R.drawable.krakownoca);
    }
}
