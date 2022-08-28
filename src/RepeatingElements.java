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
        System.out.println("mid frequencies: " + midFrequencies);

        GetAllItems();

        Contain_Mid_Frequencies_Items(midFrequencies);
    }

    public static int Contain_Mid_Frequencies_Items(double midFrequencies)
    {
        int NormalizeMidFrequencies = (int)Math.round(midFrequencies);

        if(allItems.containsValue(NormalizeMidFrequencies)) {
            GetItems_Equals_MidFrequencies(NormalizeMidFrequencies);
            return 0;
        }

        List<Integer> allValue = new ArrayList<>(allItems.values());
        allValue.add(NormalizeMidFrequencies);
        allValue = allValue.stream().sorted().collect(Collectors.toList());

        if (Collections.max(allValue) == NormalizeMidFrequencies)
        {
            GetItems_Equals_MidFrequencies(allValue.get(allValue.size()-2));
            return 0;

        } else if (Collections.min(allValue) == NormalizeMidFrequencies) {

            GetItems_Equals_MidFrequencies(allValue.get(1));
            return 0;

        }

        int getIndex = BinarySearchIndex(NormalizeMidFrequencies,allValue);
        if (getIndex != -1)
        {
            if (allValue.get(getIndex) == NormalizeMidFrequencies)
            {
                double differenceNextItem = allValue.get(getIndex+1) - midFrequencies;
                double differencePreviosItem = midFrequencies - allValue.get(getIndex-1);

                if(differenceNextItem == differencePreviosItem)
                {

                    GetItems_Equals_MidFrequencies(allValue.get(getIndex+1));
                    GetItems_Equals_MidFrequencies(allValue.get(getIndex-1));
                    return 0;

                } else if (differenceNextItem > differencePreviosItem) {

                    GetItems_Equals_MidFrequencies(allValue.get(getIndex-1));
                    return 0;
                } else {

                    GetItems_Equals_MidFrequencies(allValue.get(getIndex+1));
                    return 0;
                }

            }
        }
        return -1;

    }

    public static int BinarySearchIndex(int SercheItem, List<Integer> SortedCollection)
    {

        int fromIndex = 0;
        int toIndex = SortedCollection.size() - 1;


        while (fromIndex <= toIndex)
        {
            int mid = (toIndex + fromIndex)/2;
            if (fromIndex == toIndex)
            {
                return  fromIndex;
            }

            if (SortedCollection.get(mid)==SercheItem)
            {
                return  mid;

            } else if (SortedCollection.get(mid) < SercheItem) {

                fromIndex = mid + 1;
                toIndex = SortedCollection.size() -1;

            } else {
                fromIndex = fromIndex;
                toIndex = mid;
            }

        }
        return -1;

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



