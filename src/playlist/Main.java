package playlist;
import java.util.*;

public class Main {
//Creating a list of albums

    private static final ArrayList< Album> albums = new ArrayList<>();
    //Creating the playlist of songs
    private static final LinkedList< Song> favourite = new LinkedList<>();
    //To read input from user
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {




         Album album1 = new  Album("Recovery", "Eminem");
         Album album2 = new  Album("RockStar", "Mohit Chauhan");
    // Adding albums in the Album list
         albums.add(album1);
         albums.add(album2);
    //Adding songs in the album 1
        album1.addSong("Cold wind blows", 5.04);
        album1.addSong("Not afraid", 4.08);
        album1.addSong("Talking to myself", 5.00);
        album1.addSong("On fire", 3.33);
        album1.addSong("Space bound", 4.39);
    //adding songs from album1 to favourite (playlist)

        album1.addToPlaylist(5, favourite);
        album1.addToPlaylist(2, favourite);
        album1.addToPlaylist(3, favourite);
        album1.addToPlaylist(4, favourite);

    //Adding songs in album 2

        album2.addSong("Tum ho", 5.16);
        album2.addSong("Jo bhi main", 4.34);
        album2.addSong("Kun faya kun", 7.51);
        album2.addSong("Nadaan parindey", 3.61);
        album2.addSong("Dil ye bekarar kyun hain", 4.36);

    //Adding songs from album 2 to playlist
        album2.addToPlaylist(1, favourite);
        album2.addToPlaylist(3, favourite);
        album2.addToPlaylist(5, favourite);
        album2.addToPlaylist(2,favourite);
        album2.addToPlaylist(4,favourite);

    // starting playlist
        play(favourite);


    }
    //Displaying Playlist options for the user
    public static void printMenu() {
        System.out.println("Available Actions :\n" +
                "0 - quit \n" +
                "1 - play next song\n" +
                "2 - play previous song\n" +
                "3 - replay current song\n" +
                "4 - List the songs in the playlist\n" +
                "5 - Print options\n" +
                "6 - Delete current song\n" +
                "7 - Album Operations ");
    }

    //Displaying Album Options for the user
    private static void printAlbumOptions(){
        System.out.println("Album Menu : \n" +
                "0 - Go back to playlist operations\n" +
                "1 - Artist name \n" +
                "2 - Album name \n" +
                "3 - Add a new song to the album \n" +
                "4 - List the songs in the album \n" +
                "5 - Add a song from Album to Playlist \n" +
                "6 - Search for a song in the album \n " +
                "7 - Display options again\n");
    }

    //Operations on Albums
    private static void albumOperations(Album album) {
        boolean quit = false;
        printAlbumOptions();

        while (!quit) {
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("The artist of this album is : " + album.getArtist());
                    break;
                case 2:
                    System.out.println("The name of the album is : " + album.getAlbumName());
                    break;
                case 3:
                    System.out.println("Enter the title of the song : ");
                    String title = scanner.nextLine();
                    System.out.println("Enter the duration : ");
                    double duration = scanner.nextDouble();
                    scanner.nextLine();
                    album.addSong(title, duration);
                    break;
                case 4:
                    System.out.println("The songs in the album are : ");
                    for (int i = 0; i < album.getSongs().size(); i++) {
                        System.out.println(album.getSongs().get(i).getTitle());
                    }
                    break;
                case 5:
                    System.out.println("Enter the name of the song in the album to be added to the playlist : ");
                    String name = scanner.nextLine();
                    album.addToPlaylist(name, favourite);
                    break;
                case 6:
                    System.out.println("Enter the title you want to search in the album : ");
                    Song song = album.findSong(scanner.nextLine());
                    if (song != null) {
                        System.out.println(song.getTitle() + " is present in the album");
                    } else {
                        System.out.println("This song doesn't exist.");
                    }
                case 7:
                    printAlbumOptions();
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;


            }
        }
    }
    //Displaying contents of the playlist
    private static void printList(LinkedList< Song> playlist) {
        Iterator< Song> iterator = playlist.iterator();
        System.out.println("-------------------");
        while (iterator.hasNext()) {
            System.out.println("|"+iterator.next()+"|");
        }
        System.out.println("--------------------");
    }
    //Starting the playlist
    private static void play(LinkedList< Song> playlist) {

        boolean goingForward = true;
        boolean quit = false;
        ListIterator< Song> listIterator = playlist.listIterator();

        String playing = "Now playing ";
        if (playlist.size() == 0) {
            System.out.println("no song in playlist");
        } else {
            System.out.println(playing + listIterator.next().toString());
        }
        printMenu();
        while (!quit) {
            System.out.println("Enter option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                //Exiting playlist
                case 0:

                    System.out.println("Playlist complete");
                    quit = true;
                    break;
                //Going to the next song in the playlist
                case 1:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println(playing + listIterator.next());
                    } else {
                        System.out.println("We reached the end of the list");
                        goingForward = false;
                    }
                    break;
                // Going to previous song in the playlist
                case 2:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println(playing + listIterator.previous());
                    } else {
                        System.out.println("We are at the beginning of the list");
                        goingForward = true;
                    }
                    break;
                //Replaying the current song in the playlist
                case 3:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println(playing + listIterator.previous());
                            goingForward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println(playing + listIterator.next());
                            goingForward = true;
                        } else {
                            System.out.println("We are at the end of the list");
                        }
                    }

                    break;
                //Explained at line 136
                case 4:
                    printList(playlist);
                    break;
                 // Explained at line 152
                case 5:
                    printMenu();
                    break;
                // Deleting a song from the playlist
                case 6:
                    if (playlist.size()>0){
                        listIterator.remove();
                        if (listIterator.hasNext()){
                            System.out.println(playing + listIterator.next());
                        }else if(listIterator.hasPrevious()){
                            System.out.println(playing + listIterator.previous());
                        }
                    }
                    break;
                //Starting album operations
                case 7:
                    System.out.println("Which album to be processed : ");
                    int ch= scanner.nextInt() -1;
                    scanner.nextLine();
                    albumOperations(albums.get(ch));
                    break;

            }
        }
    }

}

