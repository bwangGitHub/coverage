package ca.uwaterloo.swag.tat;

public class Mutant
{
    private String classname;
    private String mutator;
    private int    index;

    public Mutant(String c, String m, int i)
    {
        classname = c;
        mutator = m;
        index = i;
    }

    public Mutant()
    {
        classname = "";
        mutator = "";
        index = -1;
    }

    public String getClassname()
    {
        return classname;
    }

    public void setClassname(String classname)
    {
        this.classname = classname;
    }

    public String getMutator()
    {
        return mutator;
    }

    public void setMutator(String mutator)
    {
        this.mutator = mutator;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Mutant))
        {
            return false;
        }

        Mutant m = (Mutant) obj;
        if (this.classname.equals(m.getClassname())
                && this.mutator.equals(m.getMutator())
                && this.index == m.getIndex())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return classname + "," + mutator + "," + index;
    }

    @Override
    public int hashCode()
    {
        int sum = 0;
        for (int i = 0; i < classname.length(); i++)
        {
            sum += classname.charAt(i);
        }
        for (int i = 0; i < mutator.length(); i++)
        {
            sum += mutator.charAt(i);
        }
        sum += index;
        return sum;    
    }
}
