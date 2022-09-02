//Student ID: R00221283
public class ListClient
{
    public static void main(String[] args)
    {   ListInterface<String> runnerList = new StaticArrayList<String>();
        
        runnerList.add("16");
        runnerList.add("4");
        runnerList.add("33");
        runnerList.add("27");

        runnerList.add(2,"56");
        runnerList.replace(1,"59");
        System.out.println("Is the list empty: " + runnerList.isEmpty());
        System.out.println("Does it contain the number: " + runnerList.contains("27"));


        runnerList.remove(3);
       // runnerList.clear();

        displayList(runnerList);


    }

    private static void displayList(ListInterface<String> list)
    {   int numOfEntries = list.getLength();
        
        System.out.println("The list contains " + numOfEntries + " entries, as follows:");

        for (int pos = 1; pos <= numOfEntries; pos++)
          System.out.println(list.getEntry(pos) + " is entry " + pos);
        System.out.println();
    }
}