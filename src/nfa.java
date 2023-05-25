import java.util.ArrayList;

public class nfa {

    public ArrayList<state> states;
    public ArrayList<state> acceptStates;
    public state startState;

    public nfa() {
        states = new ArrayList<>();
        acceptStates = new ArrayList<>();
    }

    public void addState(state st) {
        states.add(st);

    }

    public void addAccept(state st) {
        acceptStates.add(st);
    }


    public void asterisk() {

        for (state st : acceptStates) {
                for (pair p : startState.out) {
                    if(!st.out.contains(p)){
                    st.out.add(p);
                    p.ss.addIn(p.ff, st);}
                }



        }

        if(!startState.isAccept) {
            startState.isAccept = true;

            acceptStates.add(startState);
        }
    }

    public void or(nfa nf) {

        for (pair p : nf.startState.out) {
            if (p.ss != nf.startState) {
                startState.out.add(p);
                p.ss.addIn(p.ff, startState);
                p.ss.in.remove(p);
            } else {
                startState.out.add(new pair(p.ff, startState));

            }
        }

        for (pair p : nf.startState.in) {
            if (p.ss != nf.startState) {
                startState.in.add(p);
                p.ss.addOut(p.ff, startState);
                p.ss.out.remove(p);
            } else {
                startState.addIn(p.ff, startState);
            }
        }
        if (nf.startState.isAccept) {
            if(!startState.isAccept){
            startState.isAccept = true;
            acceptStates.add(startState);}
            nf.acceptStates.remove(nf.startState);
        }
        nf.states.remove(nf.startState);
        acceptStates.addAll(nf.acceptStates);
        states.addAll(nf.states);

        indexate();

    }

    public void concat(nfa nf) {
        if (nf.states.size() == 0) return;

        for (state st : acceptStates) {
            if (!nf.startState.isAccept)
                st.isAccept = false;
            else if( !nf.acceptStates.contains(st)) {
                nf.addAccept(st);
            }
            for (pair p : nf.startState.out) {
                if (p.ss != nf.startState) {
                    st.out.add(p);
                    p.ss.addIn(p.ff, st);
                    p.ss.in.remove(new pair(p.ff, nf.startState));
                }
            }
            for (pair p : nf.startState.in) {
                if (p.ss != nf.startState) {
                    st.in.add(p);
                    p.ss.addOut(p.ff, st);
                    p.ss.out.remove(new pair(p.ff, nf.startState));

                } else {
                    st.addIn(p.ff, st);
                    st.addOut(p.ff, st);
                }
            }

        }
        acceptStates.clear();
        acceptStates.addAll(nf.acceptStates);
        if (nf.startState.isAccept) {
            acceptStates.remove(nf.startState);
        }
        nf.states.remove(nf.startState);

        states.addAll(nf.states);
        indexate();

    }

    public void indexate() {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).index = i;
        }
    }


}
