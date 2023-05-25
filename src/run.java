import java.util.Arrays;
import java.util.Scanner;

public class run {

   public static String input;
    private  static int nStates;
    private static int nAccepts;
   private static int edges;
    private static boolean answers[];
   private static Scanner sc;
    static StringBuilder p;
    public static void main(String[] args) {
         sc = new Scanner(System.in);
        input=sc.nextLine();
        nStates = sc.nextInt();
        nAccepts = sc.nextInt();

        edges = sc.nextInt();
        answers = new boolean[input.length()];
        Arrays.fill(answers,false);

        nfa nfa = new nfa();
        fillNfa(nfa);
        fillAccepts(nfa);
        simulateNfa(nfa);
        dfs(nfa.startState,0);

         p = new StringBuilder();

        for(boolean i: answers){
            if(i){
                p.append("Y");
            }else p.append("N");
        }
        System.out.println(new String(p));

    }

    private static void simulateNfa(nfa nf) {
        nf.startState = nf.states.get(0);
        for(int i=0; i<nStates;i++){
            int m = sc.nextInt();

            state st= nf.states.get(i);

            for(int j=0;j<m;j++){
                char c = sc.next().charAt(0);
                int s = sc.nextInt();

                state curr = nf.states.get(s);
                st.addOut(c,curr);
            }
        }
    }

    private static void fillAccepts(nfa nf) {
        for(int i=0; i<nAccepts;i++){
            int index = sc.nextInt();
            state st = nf.states.get(index);
            st.isAccept=true;
            nf.addAccept(st);
        }
    }

    private static void fillNfa(nfa nf) {
        for(int i=0; i<nStates; i++){
            state st = new state();
            nf.addState(st);
            st.index=i;
        }
    }

    private static void dfs(state curr, int i) {
        for (pair out: curr.out) {
            if(out.ff ==input.charAt(i))
                dfs(out.ss,i+1);
        }
        if(curr.isAccept && i>0){
            answers[i-1]=true;
        }}
}