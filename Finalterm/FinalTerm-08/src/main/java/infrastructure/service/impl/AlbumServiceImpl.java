package infrastructure.service.impl;

import core.dto.AlbumDTO;
import core.entity.Album;
import infrastructure.config.JPAUtil;
import infrastructure.mapper.Mapper;
import infrastructure.repository.AlbumRepository;
import infrastructure.repository.impl.AlbumRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlbumServiceImpl extends UnicastRemoteObject implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl() throws RemoteException {
        albumRepository = new AlbumRepositoryImpl();
    }

    @Override
    public boolean updatePriceOfAlbum(String id, double newPrice) throws RemoteException{
        return albumRepository.updatePriceOfAlbum(id, newPrice);
    }

    @Override
    public List<AlbumDTO> listAlbumByGenre(String genreName, int year) throws RemoteException{
        return albumRepository.listAlbumByGenre(genreName, year).stream().map(Mapper::toDTO).toList();
    }

    @Override
    public Map<String, Long> getNumberOfAlbumsByGenre() throws RemoteException{
        return albumRepository.getNumberOfAlbumsByGenre();
    }

}
