import java.util.*;
public class assembler_in_ja {
    static HashMap<String,String> h = new HashMap<>();
    public static void main(String[] args) {
        addOpcode();
        addRegister();
        //System.out.println(Integer.toHexString(Integer.parseInt("1010000000100010000000000000011",2)));
        realExecution();
    }
    static void addOpcode(){
        h.put("add","000000");
        h.put("mov","001000");
        h.put("sub","010000");
        h.put("and","011000");
        h.put("adi","100000");
        h.put("lda","101000");
        h.put("sta","110000");
        h.put("mvi","111000");
    }
    static void addRegister(){
        for(int i=0;i<=31;i++){
            h.put("r"+i,immediate(Integer.toBinaryString(i),5));
        }
    }
    static String immediate(String s,int y){
        if(y!=5){
            //System.out.println("kjsddsf");
            s = Integer.toBinaryString(Integer.parseInt(s,16));
        }
        while(s.length()<y){
            s = "0"+s;
        }
        return s;
    }
    static String padding(String s,int y){
        while(s.length()<y)
            s = "0"+s;
        return s;
    }
    static void realExecution(){
        Scanner in = new Scanner(System.in);
        ArrayList<String> a = new ArrayList<>();
        System.out.print("Enter the number of instruction you want to execute : ");
        int t = in.nextInt();
        in.nextLine();
        while(t-->0){
            String s = in.nextLine();
            //System.out.println(s.substring(10));
            switch(s.substring(0,3)){
                case "add":
                    a.add(h.get("add")+h.get(s.substring(4,6))+""+h.get(s.substring(7,9))+""+h.get(s.substring(10,12))+"00000000000");break;
                case "sub":
                    a.add(h.get("sub")+h.get(s.substring(4,6))+""+h.get(s.substring(7,9))+""+h.get(s.substring(10,12))+"00000000000");break;
                case "adi":
                    a.add(h.get("adi")+h.get(s.substring(4,6))+""+h.get(s.substring(7,9))+""+immediate(s.substring(10),16));break;
                case "mov":
                    a.add(h.get("mov")+h.get(s.substring(4,6))+""+h.get(s.substring(7,9))+"0000000000000000");break;
                case "mvi":
                    a.add(h.get("mvi")+h.get(s.substring(4,6))+"00000"+immediate(s.substring(7),16));break;
                case "and":
                    a.add(h.get("add")+h.get(s.substring(4,6))+""+h.get(s.substring(7,9))+""+h.get(s.substring(10,12))+"00000000000");break;
                case "lda":
                    a.add(h.get("lda")+h.get(s.substring(4,6))+""+h.get(s.substring(12,14))+""+immediate(s.substring(7,11),16));break;
                case "sta":
                    a.add(h.get("sta")+h.get(s.substring(4,6))+""+h.get(s.substring(12,14))+""+immediate(s.substring(7,11),16));break;
            }
        }
        for(int i=0;i<a.size();i++){
            String s  = a.get(i);
            //System.out.println(s);
            String s1 = Integer.toHexString(Integer.parseInt(s.substring(0,4),2));
            String s2 = Integer.toHexString(Integer.parseInt(s.substring(4),2));
            //System.out.println(s1+" "+s2);
            System.out.println((s1+padding(s2,7)));
        }
    }
}
