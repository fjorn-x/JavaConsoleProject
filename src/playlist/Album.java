package playlist;


import java.util.ArrayList;
import java.util.LinkedList;


public class Album {
    private final ArrayList<Song> songs;
    private final String albumName;
    private final String artist;


    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return artist;
    }
    //Initializing instances in a constructor
    public Album(String albumName, String artist) {
        this.albumName = albumName;
        this.artist=artist;
        this.songs=new ArrayList<>();
    }
    // functionality to add a song
    public void addSong(String title, double duration){
        Song song=findSong(title);
        if (song==null){
            Song newSong =new Song(title,duration);
            songs.add(newSong);
        }else{
            System.out.println("Song "+ title +" is already in the album");
        }
    }

    //function to check if the song already exists in the album(validation )

    protected Song findSong(String title){

        for (Song checkSong : this.songs) {
            if (checkSong.getTitle().equals(title)) {
                return checkSong;
            }
        }
        return null;
    }

    //function to add an existing song to a playlist(title)
    public void addToPlaylist(String title,LinkedList<Song> playlist){
        Song checkSong=findSong(title);
        if (checkSong!=null){
            playlist.add(checkSong);


        }else {
            System.out.println(title + " is not in the album");
        }
    }
    //function to add an existing song to a playlist(track number)

    public void addToPlaylist(int trackNumber, LinkedList<Song> playlist){
        int index=trackNumber-1;
        if ((index>=0)&&(index<=this.songs.size())){
            playlist.add(this.songs.get(index));

        }else {
            System.out.println("this album does not have a " + trackNumber );
        }
    }
}
