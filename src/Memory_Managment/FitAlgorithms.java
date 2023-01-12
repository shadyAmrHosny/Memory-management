package Memory_Managment;

import java.util.ArrayList;
import java.util.Iterator;

public class FitAlgorithms {
    private final ArrayList<Partition> partitions;
    private final ArrayList<Process> processes;
    int CurrentPartition;
    FitAlgorithms(ArrayList<Partition> p, ArrayList<Process> ps)
    {
        this.partitions=p;
        this.processes=ps;
        CurrentPartition=partitions.size();
    }
    public void  First_Fit()
    {
        for (Process process : processes)
        {
            for (Partition partition : partitions)
            {
                if (process.getSize() <= partition.getSize() && partition.isFree()&& !process.isFlag())
                {
                    partition.Lock();
                    process.check();
                    partition.setP(process);
                    if (partition.getSize()-process.getSize()>0) AdditionalPartition(partition.getSize());
                    break;
                }
            }
        }
    }
    public void Best_Fit()
    {
        ArrayList<Partition>tmp=SortedArray();
        ArrayList<Integer>Indexes=SortedIndexes(tmp);
        int index;
        for (Process process : processes)
        {
            for (int j=0;j<Indexes.size();j++)
            {
                index=Indexes.get(j);
                if (process.getSize()<=partitions.get(index).getSize()&&partitions.get(index).isFree())
                {
                    partitions.get(index).Lock();
                    process.check();
                    partitions.get(index).setP(process);
                    if (partitions.get(index).getSize()-process.getSize()>0)AdditionalPartition(partitions.get(index).getSize());
                    tmp=SortedArray();
                    Indexes=SortedIndexes(tmp);
                    break;
                }
            }
        }
    }
    public void Worst_Fit()
    {
        int max;
        for (Process process : processes)
        {
            max = MaxFreePartition();
            if (process.getSize() <= partitions.get(max).getSize() && partitions.get(max).isFree())
            {
                partitions.get(max).Lock();
                process.check();
                partitions.get(max).setP(process);
                if (partitions.get(max).getSize()-process.getSize()>0)AdditionalPartition(partitions.get(max).getSize());
            }
        }
    }
    public void Compact()
    {
        double sum=0;
        for (int i=0;i<partitions.size();i++)
        {
            if (partitions.get(i).isFree())
            {
                sum+=partitions.get(i).getSize();
                partitions.remove(i);
                i--;
            }
        }
        partitions.add(new Partition("Partition "+CurrentPartition,sum));
        CurrentPartition++;
        First_Fit();
    }
    public void AdditionalPartition(double x)
    {
        Iterator<Partition> iterator=partitions.iterator();
        while (iterator.hasNext())
        {
            Partition tmp=iterator.next();
            if (tmp.getSize()==x)
            {
                ArrayList<Partition> s=new ArrayList<>();
                while(iterator.hasNext())
                {
                    s.add(iterator.next());
                }
                partitions.removeAll(s);
                partitions.add(new Partition("Partition "+CurrentPartition,tmp.getSize()-tmp.getP().getSize()));
                partitions.addAll(s);
                break;
            }
        }
        CurrentPartition++;
    }
    public int MaxFreePartition()
    {
        Partition tmp= partitions.get(0);
        int indexofmax=0;
        for (int i=1;i<partitions.size();i++)
        {
            if(tmp.getSize()< partitions.get(i).getSize()&& partitions.get(i).isFree())
            {
                tmp= partitions.get(i);
                indexofmax=i;
            }
        }
        return indexofmax;
    }
    public ArrayList<Partition> SortedArray()
    {
        ArrayList<Partition> sorted = new ArrayList<>();
        Partition tmp;
        for (Partition partition : partitions)
        {
            sorted.add(partition);
        }
        for (int i = 0; i < sorted.size(); i++)
        {
            for (int j = i + 1; j < sorted.size(); j++)
            {
                if (sorted.get(j).getSize() < sorted.get(i).getSize())
                {
                    tmp = sorted.get(i);
                    sorted.set(i,sorted.get(j));
                    sorted.set(j,tmp);
                }
            }
        }
        return sorted;
    }
    public int Search(double x)
    {
        for (int i=0;i<partitions.size();i++)
        {
            if (partitions.get(i).getSize()==x)return i;
        }
        return -1;
    }
    public ArrayList<Integer> SortedIndexes(ArrayList<Partition> tmp)
    {
       ArrayList<Integer>Indexes = new ArrayList<>();
       int index;
        for (Partition partition : tmp) {
            index = Search(partition.getSize());
            Indexes.add(index);
        }
        return Indexes;
    }
    public void Print()
    {
        for (Partition partition : partitions)
        {
            if (partition.isFree())
            {
                System.out.println(partition.getName() + "  (" + partition.getSize() + " KB ) => External fragment");
            } else
            {
                System.out.println(partition.getName() + "  (" + partition.getP().getSize() + " KB ) => " + partition.getP().getName());
            }
        }
        for (Process process : processes)
        {
            if (!process.isFlag())
            {
                System.out.println(process.getName() + " can not be allocated");
            }
        }
    }
}
