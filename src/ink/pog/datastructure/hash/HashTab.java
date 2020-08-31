package ink.pog.datastructure.hash;

import javax.crypto.interfaces.PBEKey;

public class HashTab {
    private final Integer MAX_SIZE = 10;
    EmployeeList employeeList[];
    public HashTab(){
        employeeList = new EmployeeList[MAX_SIZE];
        for (Integer i = 0; i < MAX_SIZE; i++) {
            employeeList[i] = new EmployeeList();
        }
    }

    public int getHash(int id){
        return id % MAX_SIZE;
    }

    public void add(Employee employee){
        int hashIndex = getHash(employee.id);
        employeeList[hashIndex].add(employee);
    }

    public void list(){
        for (Integer i = 0; i < MAX_SIZE; i++) {
            employeeList[i].list();
            System.out.println();
        }
    }

    public Employee find(int id){
        int hashIndex = getHash(id);
        Employee employee = employeeList[hashIndex].find(id);
        return employee;
    }

    public void del(int id){
        int hashIndex = getHash(id);
        employeeList[hashIndex].del(id);
    }


    public static void main(String[] args) {

        HashTab hashTab = new HashTab();
        hashTab.add(new Employee(1,"pog"));
        hashTab.add(new Employee(21,"pog"));
        hashTab.add(new Employee(3,"pog"));
        hashTab.add(new Employee(13,"pog"));
        hashTab.add(new Employee(4,"pog"));
        hashTab.add(new Employee(5,"pog"));
        hashTab.add(new Employee(6,"pog"));
        hashTab.del(11);
        hashTab.list();

    }






}

class EmployeeList{

    private Employee head;


    public void add(Employee employee){
        if(head == null){
            head = employee;
            return;
        }
        Employee temp = head;
        while (temp.next != null){
            if(employee.id == temp.id){
                System.out.println("id已存在");
                return;
            }
            temp = temp.next;
        }
        temp.next = employee;
    }

    public void list(){
        if(head == null){
            System.out.print("当前链表为空");
            return;
        }
        Employee temp = head;
        while (temp != null){
            System.out.print(temp + " => ");
            temp = temp.next;
        }
    }

    public Employee find(int id){
        Employee temp = head;
        while (true){
            if(temp == null){
                System.out.println("没有此id");
                return null;
            }
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
    }

    public void del(int id){
        if(head == null){
            System.out.println("链表有空");
            return;
        }

        if(head.id == id){
            head = head.next;
            return;
        }
        Employee temp = head;
        while (true){
            if(temp.next == null){
                System.out.println("没有此ID");
                break;
            }
            if(temp.next.id == id){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }


    }

}




class Employee{

    Integer id;
    String name;
    Employee next;
    public Employee(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    public Employee(){

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}



