import java.util.ArrayList;

public class state implements Comparable<state>{

    public int index;
    public ArrayList<pair> in,out;
    boolean isAccept;

    public state(){
        isAccept=false;
        in= new ArrayList<>();
        out= new ArrayList<>();
        index=0;
    }
    public void addIn(char c, state st){

        in.add(new pair(c,st));
    }

    public void addOut(char c, state st){
        out.add(new pair(c,st));
    }


    @Override
    public int compareTo(state o) {
        return this.index-o.index;
    }
}
