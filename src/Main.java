public class Main {

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void main(String[] args)
    {
         IConectionApi conectGet = new ConectionApi();
         var response = conectGet.Get("http://numbersapi.com/"+ rnd(1,1000) +"/trivia");
         System.out.println(response);
         RepeatingElements.GetRepitItem(response);

    }

}