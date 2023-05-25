public class pair {
    char ff;
    state ss;
    public pair(char x, state y){
        this.ff =x;
        this.ss =y;
    }
    public pair(pair p){
        ff=p.ff;
        ss =p.ss;
    }
}
