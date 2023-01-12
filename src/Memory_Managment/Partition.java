package Memory_Managment;

public class Partition {
    private String Name;
    private double Size;
    private Process p=null;
    private boolean Free=true;
    Partition()                           //  Default Constructor
    {
        Name="NONE";
        Size=0.00;
    }
    Partition(String Name,double s)       //  Parameterized Constructor
    {
        this.Name=Name;
        this.Size=s;
    }
    //  Setters
    public void setName(String name)
    {
        Name = name;
    }

    public void setSize(double size)
    {
        Size = size;
    }

    public void setP(Process p)
    {
        this.p = p;
    }
    public void Lock()
    {
        Free = false;
    }

    //  Getters
    public double getSize()
    {
        return Size;
    }

    public String getName()
    {
        return Name;
    }

    public Process getP()
    {
        return p;
    }

    public boolean isFree()
    {
        return Free;
    }
}
