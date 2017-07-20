package ca.uwaterloo.swag.tat;

public class UnitTest
{
    private String classname;
    private String methodname;

    public UnitTest(String fullname)
    {
        String[] names = splitTestName(fullname);
        classname = names[0];
        methodname = names[1];
    }

    public UnitTest(String c, String m)
    {
        classname = c;
        methodname = m;
    }

    private String[] splitTestName(String fullname)
    {
        String[] splitName = new String[2];
        int splitPoint = fullname.lastIndexOf(".");
        splitName[0] = fullname.substring(0, splitPoint);
        splitName[1] = fullname.substring(splitPoint + 1, fullname.length());
        return splitName;
    }

    public String getClassname()
    {
        return classname;
    }

    public String getMethodname()
    {
        return methodname;
    }

    @Override
    public boolean equals(Object o)
    {
        if ((o instanceof UnitTest)
	    && (((UnitTest) o).getClassname().equals(classname))
	    && (((UnitTest) o).getMethodname().equals(methodname)))
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return classname + "." + methodname;
    }

    @Override
    public int hashCode()
    {
        int sum = 0;
        for (int i = 0; i < classname.length(); i++)
        {
            sum += classname.charAt(i);
        }
        for (int i = 0; i < methodname.length(); i++)
        {
            sum += methodname.charAt(i);
        }
        return sum;    
    }
}
