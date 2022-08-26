import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepeatingElements {

    public static HashMap<Character,Integer> allItems = new HashMap<Character,Integer>();

    public static  void GetRepitItem(String inputItem)
    {
        for (int i = 0; i < inputItem.length(); i++)
        {
            char item = inputItem.charAt(i);
            if (item == ' ')
            {
                continue;
            }

            if (allItems.containsKey(item))
            {
                int oldValue =  allItems.get(item);
               allItems.replace(item, oldValue + 1 );
            } else{
                allItems.put(item,1);
            }
        }
        ConsoleOutputFrequencies();
    }

    public static void ConsoleOutputFrequencies()
    {
        int ALLFrequencies = allItems.values().stream().mapToInt(a->a).sum();
        double midFrequencies = (double) ALLFrequencies / allItems.size();

        int Max_res = (int)Math.round(midFrequencies);

        System.out.println("ALLFrequencies: " + ALLFrequencies);
        System.out.println("mid frequencies: " + Max_res);

        GetAllItems();

        Contain_Mid_Frequencies_Items(Max_res);
    }

    public static void Contain_Mid_Frequencies_Items(int midFrequencies)
    {
        if(allItems.containsValue(midFrequencies)) {
            GetItems_Equals_MidFrequencies(midFrequencies);
            return;
        }

        List<Integer> allValue = new ArrayList<>(allItems.values());
        allValue.add(midFrequencies);
        allValue = allValue.stream().sorted().collect(Collectors.toList());

        if (Collections.max(allValue) == midFrequencies)
        {
            GetItems_Equals_MidFrequencies(allValue.get(allValue.size()-1));
            return;

        } else if (Collections.min(allValue) == midFrequencies) {

            GetItems_Equals_MidFrequencies(allValue.get(1));
            return;

        }


        for (int i = 0; i < allValue.size(); i++)
        {
            if (allValue.get(i) == midFrequencies)
            {
                int differenceNextItem = allValue.get(i+1) - allValue.get(i);
                int differencePreviosItem = allValue.get(i) - allValue.get(i-1);

                if(differenceNextItem == differencePreviosItem)
                {

                    GetItems_Equals_MidFrequencies(allValue.get(i+1));
                    GetItems_Equals_MidFrequencies(allValue.get(i-1));
                    return;

                } else if (differenceNextItem > differencePreviosItem) {

                    GetItems_Equals_MidFrequencies(allValue.get(i-1));
                    return;
                } else {

                    GetItems_Equals_MidFrequencies(allValue.get(i+1));
                    return;
                }

            }
        }


    }


    public static void GetAllItems()
    {
        for (char Key:
                allItems.keySet()) {

            System.out.println(Key + " " + allItems.get(Key));
        }
    }

    public static void GetItems_Equals_MidFrequencies(int Frequencies)
    {
        for (char Key:
                allItems.keySet()) {

            if (allItems.get(Key) == Frequencies)
            {
                System.out.println("item: " + Key + ". utf_8 code: " + (int)Key);
            }

        }
    }
}



