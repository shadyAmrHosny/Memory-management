package Memory_Managment;

public class Process {
    private String Name;
    private double Size;
    private boolean Flag;
    Process()                           //  Default Constructor
    {
        Name="NONE";
        Size=0.00;
        Flag= false;
    }
    Process(String Name,double s)       //  Parameterized Constructor
    {
        this.Name=Name;
        this.Size=s;
        Flag=false;
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
    public void check()
    {
        Flag=true;
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
    public boolean isFlag()
    {
        return Flag;
    }
}
