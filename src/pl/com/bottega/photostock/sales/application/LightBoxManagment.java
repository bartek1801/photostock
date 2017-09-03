package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.LightBoxRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.repositories.ReservationRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by bartek on 03.09.2017.
 */
public class LightBoxManagment {

    private LightBoxRepository lightBoxRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private ReservationRepository reservationRepository;

    public LightBoxManagment(LightBoxRepository lightBoxRepository, ClientRepository clientRepository,
                             ProductRepository productRepository, ReservationRepository reservationRepository) {
        this.lightBoxRepository = lightBoxRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
    }

    public String create(String clientNumber, String lightBoxName){
        Client client = clientRepository.get(clientNumber);
        LightBox lightBox = new LightBox(client, lightBoxName);
        lightBoxRepository.save(lightBox);
        return lightBox.getNumber();
    }

    void add(String lightBoxNumber, Long pictureNumber){
        Product product = productRepository.get(pictureNumber);
        if (!(product instanceof Picture)) {
            throw new IllegalArgumentException(String.format("Product %s is not a picture", pictureNumber ));
        }
        LightBox lightBox = lightBoxRepository.get(lightBoxNumber);
        Picture picture = (Picture) product;
        lightBox.add(picture);
        lightBoxRepository.save(lightBox);
    }

   /* void share(String lightBoxNumber, String userNumber){
        LightBox lightBox = lightBoxRepository.get(lightBoxNumber);
        Client client = clientRepository.get(userNumber);
        //sprawdzamy czy właściciel lightboxa i wskazany użytkownik należą do tej samej firmy

        //oznaczamy wskazanego użytkownika jako współwłaściciela lightboxa

        lightBoxRepository.save(lightBox);
    }*/

   void reserve(String lightBoxNumber, Set<Long> pictureNumbers, String reservationNumber){
       LightBox lightBox = lightBoxRepository.get(lightBoxNumber);
       Reservation reservation = reservationRepository.get(reservationNumber);
       List<Picture> pictures = lightBox.getPictures(pictureNumbers);
       if (pictureNumbers.size() != pictures.size())
           throw new IllegalArgumentException("Invalid product numbers");
       for (Picture picture : pictures){
           picture.ensureAvailable();
       }
       for (Picture picture : pictures){
           reservation.add(picture);
           productRepository.save(picture);
       }
       reservationRepository.save(reservation);
   }

}
