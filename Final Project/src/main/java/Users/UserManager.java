package Users;

import java.io.*;
import java.util.Objects;

public class UserManager {
    public int num_users;
    public User[] users;
    public String path = "Final Project\\src\\main\\java\\Users\\users-save.txt";
    public void GetUsers() throws IOException { //ler primeiro numero de linhas, cria lista de usuarios com esse numero, cria users com as infos das linhas
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));
        int counter = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            counter ++;
        }
        num_users = counter;
        users = new User[num_users];
        counter = 0;
        br = new BufferedReader(new FileReader(file));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String [] splits = line.split(" ");
            String name = splits[0];
            int wins = Integer.parseInt(splits[1]);
            int losses = Integer.parseInt(splits[2]);
            int ties = Integer.parseInt(splits[3]);

            users[counter] = new User(name, wins, losses, ties);
            counter ++;
        }
    }
    public void SaveUsers() throws IOException { //pega os usuarios do arquivo e escreve no arquivo
        GetUsers();
        FileWriter myWriter = new FileWriter(path);
        for(int i = 0; i < num_users; i++){
            if(i<num_users-1) {
                String line = users[i].name + " " + users[i].wins + " " + users[i].losses + " " + users[i].ties + "\n";
                myWriter.write(line);
            }
            else {
                String line = users[i].name + " " + users[i].wins + " " + users[i].losses + " " + users[i].ties;
                myWriter.write(line);
            }
        }
        myWriter.close();
    }
    public void SaveUsersDirectly() throws IOException {  //diferença: não chama GetUsers, ou seja, não carrega do arquivo txt, e sim da propria lista de Users
        FileWriter myWriter = new FileWriter(path);
        for(int i = 0; i < num_users; i++){
            if(i<num_users-1) {
                String line = users[i].name + " " + users[i].wins + " " + users[i].losses + " " + users[i].ties + "\n";
                myWriter.write(line);
            }
            else {
                String line = users[i].name + " " + users[i].wins + " " + users[i].losses + " " + users[i].ties;
                myWriter.write(line);
            }
        }
        myWriter.close();
    }
    public void CreateUser(String name) throws IOException {
        SaveUsers();
        User user = new User(name);
        FileWriter myWriter = new FileWriter(path, true);

        String line;
        if(num_users>0) {
            line = "\n" + user.name + " 0 0 0";
        }
        else {
            line = user.name + " 0 0 0";
        }
        myWriter.write(line);
        myWriter.close();
        num_users++;
        SaveUsers();
    }
    public int findUser(String name){
        for(int i = 0; i< num_users; i++){
            if (Objects.equals(users[i].name, name)) return i;
        }
        return -1;
    }
    public void DeleteUser(String name) throws IOException {
        SaveUsers();
        int user_id = findUser(name);
        if (user_id != -1){
            for(int i = user_id; i < num_users-1; i++){
                users[i] = users[i+1];
            }
            users[num_users-1] = new User();
            num_users --;
            SaveUsersDirectly();
            if (num_users==0)CreateUser("jogador1");
            SaveUsers();
        }

    }
}
