package infrastructure.service.impl;

import core.dto.AlbumDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface AlbumService extends Remote {
    boolean updatePriceOfAlbum(String id, double newPrice) throws RemoteException;

    List<AlbumDTO> listAlbumByGenre(String genreName, int year) throws RemoteException;

    Map<String, Long> getNumberOfAlbumsByGenre() throws RemoteException;
}
