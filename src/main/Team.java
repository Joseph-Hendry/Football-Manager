package main;

class Team {
    static ArrayList<Team> teamList;
    String name;
    ArrayList<Player> onTeam;
    ArrayList<Player> onBench;
    ArrayList<Item> 
    Coach coach;
    int rank;

    public Team(String name, ArrayList<Player> onTeam, ArrayList<Player> onBench, Coach coach, int rank) {
        this.name = name;
        this.onTeam = onTeam;
        this.onBench = onBench;
        this.coach = coach;
        this.rank = rank;
    }

    public string getName() {
        return this.name;
    }
}