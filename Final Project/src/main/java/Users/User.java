package Users;

public class User {
    public String name;
    public int wins = 0;
    public int losses = 0;
    public int ties = 0;
    public User(){}
    public User(String name){
        this.name = name;
    }
    public User(String name, int wins, int losses, int ties){
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }
    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }
}
