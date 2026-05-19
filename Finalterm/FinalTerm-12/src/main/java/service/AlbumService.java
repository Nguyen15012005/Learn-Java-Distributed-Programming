package service;

import dto.AlbumDTO;
import entity.Album;
import repository.AlbumRepository;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class AlbumService extends UnicastRemoteObject {
    private final AlbumRepository albumRepository;

    public AlbumService() throws RemoteException {
        albumRepository = new AlbumRepository();
    }

    public boolean updatePriceOfAlbum(String id, double newPrice)throws RemoteException{
        return albumRepository.updatePriceOfAlbum(id,newPrice);
    }

    public List<AlbumDTO> listAlbumByGenre(String genreName, int year) throws RemoteException{
        return albumRepository.listAlbumByGenre(genreName,year).stream().map(

                o -> new AlbumDTO(
                        o.getId(),
                        o.getTitle(),
                        o.getPrice(),
                        o.getYearOfRelease(),
                        o.getGenre().getName(),
                        o.getDownloadLink()
                )
        ).toList();

    }

    public Map<String, Long> getNumberOfAlbumsByGenre()throws RemoteException{
        return albumRepository.getNumberOfAlbumsByGenre();
    }
}
