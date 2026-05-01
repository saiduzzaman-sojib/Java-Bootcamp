public class Student {
    String name;
    private int id;
    double cgpa;

    void setId(int id){
        if (id<0){
            System.out.println("Invalid");
        }else {
            this.id=id;
        }
    }
    void setCgpa(double cgpa){
        if (cgpa<0){
            System.out.println("Invalid");
        }else {
            this.cgpa=cgpa;
        }
    }
    int  getID()
    {
        return id;
    }
}
