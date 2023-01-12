package Memory_Managment;
import java.util.ArrayList;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin =new Scanner(System.in);
        int NProcesses,NPartitions,Select;
        double size;
        System.out.print("ENTER NUMBER OF PARTITIONS:");
        NPartitions=cin.nextInt();
        ArrayList<Partition> partitions= new ArrayList<>();
        for (int i=0;i<NPartitions;i++)
        {
            System.out.print("ENTER SIZE OF PARTITION"+i+':');
            size=cin.nextDouble();
            partitions.add(new Partition("Partition "+i,size));
        }
        System.out.print("ENTER NUMBER OF PROCESS:");
        NProcesses=cin.nextInt();
        ArrayList<Process> processes= new ArrayList<>();
        for (int i=0;i<NProcesses;i++)
        {
            System.out.print("ENTER SIZE OF PROCESS"+(i+1)+':');
            size= cin.nextDouble();
            processes.add(new Process("Process "+(i+1),size));
        }
        FitAlgorithms policy=new FitAlgorithms(partitions,processes);
        System.out.println("SELECT THE POLICY YOU WANT TO APPLY:");
        System.out.println("1 - FIRST FIT");
        System.out.println("2 - BEST FIT");
        System.out.println("3 - WORST FIT");
        Select=cin.nextInt();
        switch (Select)
        {
            case 1 ->
            {
                policy.First_Fit();
                policy.Print();
            }
            case 2 ->
            {
                policy.Best_Fit();
                policy.Print();
            }
            case 3 ->
            {
                policy.Worst_Fit();
                policy.Print();
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println("DO YOU WANT TO COMPACT:  1-YES    /    2-NO");
        Select=cin.nextInt();
        if (Select==1)
        {
            System.out.println("------------------------------------------------");
            policy.Compact();
        }
        policy.Print();
    }
}
