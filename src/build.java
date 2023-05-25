import java.util.Collections;
import java.util.Scanner;

public class build {

    static int i = 0;
    static String input;
    public static String answer;
    private static boolean isLegal(char c) {

        return Character.isLetter(c) || Character.isDigit(c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();


        nfa nf = new nfa();
        makeStart(nf);

        rec(nf);

        int cnt = 0;
        for (state st : nf.states) {
            cnt += st.out.size();
        }

        System.out.println(nf.states.size() + " " + nf.acceptStates.size() + " " + cnt);
//        answer= nf.states.size() + " " + nf.acceptStates.size() + " " + cnt+"\n";
        Collections.sort(nf.acceptStates);
        for (state st : nf.acceptStates) {
            System.out.print(st.index + " ");
//            answer+=st.index + " ";
        }
        System.out.println("");
//        answer+="\n";
        for (state st : nf.states) {
            if (st.out.size() == 0) {
                System.out.println(0);
//                answer+=0+"\n";

            } else {
                System.out.println(st.out.size() + " " + getStr(st));
//                answer+=st.out.size() + " " + getStr(st)+"\n";
            }

        }
//        System.out.println(answer);

    }

    public static String getStr(state st) {
        StringBuilder s = new StringBuilder();
        for (pair p : st.out) {
            s.append(p.ff).append(" ").append(p.ss.index).append(" ");

        }
        return new String(s);
    }

    private static void makeStart(nfa nf) {
        nf.startState = new state();
        nf.addState(nf.startState);
        nf.addAccept(nf.startState);
        nf.startState.isAccept = true;
    }

    private static void rec(nfa nf) {
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '|') {
                i++;
                nfa one = new nfa();
                makeStart(one);
                rec(one);
                nf.or(one);
                continue;
            }

            if (c == ')') {
                return;
            }
            if (c == '*') {
                nf.asterisk();
                i++;
                continue;
            }
            if (c == '(') {
                i++;
                nfa one = new nfa();
                makeStart(one);
                rec(one);

                if (i < input.length() - 1 && input.charAt(i + 1) == '*') {
                    one.asterisk();
                }

                i++;
                nf.concat(one);
                continue;
            }
            if (isLegal(c)) {
                nfa one = new nfa();
                makeAutomata(one);

                if (i < input.length() - 1 && input.charAt(i + 1) == '*') {
                    one.asterisk();
                    i++;
                }
                i++;
                nf.concat(one);

            }

        }
    }

    private static void makeAutomata(nfa one) {
        one.startState = new state();
        one.addState(one.startState);
        one.addState(new state());
        one.addAccept(one.states.get(1));
        one.states.get(1).isAccept = true;
        one.startState.addOut(input.charAt(i), one.states.get(1));
        one.states.get(1).addIn(input.charAt(i), one.startState);
        one.indexate();
    }

}
