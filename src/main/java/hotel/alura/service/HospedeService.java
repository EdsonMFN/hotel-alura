package hotel.alura.service;

import java.util.ArrayList;
import java.util.List;

import hotel.alura.domains.entities.Reserva;
import hotel.alura.domains.model.HospedeDto;
import hotel.alura.exception.handling.ExceptinHandler;
import hotel.alura.rest.request.RequestHospede;
import hotel.alura.rest.response.ResponseHospede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.alura.domains.entities.Hospede;
import hotel.alura.domains.repository.HospedeRepository;
import hotel.alura.domains.repository.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HospedeService {
    
    @Autowired
    private HospedeRepository hospedeRepository;    

    @Autowired
    private ReservaRepository reservaRepository;

    public ResponseHospede insertHospede(Long idReserva, RequestHospede requestHospede){
    	 Reserva reserva= reservaRepository.findById(idReserva).map(a->a)
                 .orElseThrow(() -> new EntityNotFoundException("A reserva ID:" + idReserva + " não existe."));

            Hospede hospede = new Hospede();
            hospede.setNome(requestHospede.getNome());
            hospede.setSobrenome(requestHospede.getSobrenome());
            hospede.setDataNascimento(requestHospede.getDataNascimento());
            hospede.setNacionalidade(requestHospede.getNacionalidade());
            hospede.setTelefone(requestHospede.getTelefone());
            hospedeRepository.save(hospede);

            reserva.getHospedes().add(hospede);
            reservaRepository.save(reserva);

            return new ResponseHospede(HospedeDto
                    .builder()
                    .id(hospede.getId())
                    .nome(hospede.getNome())
                    .sobrenome(hospede.getSobrenome())
                    .dataNascimento(hospede.getDataNascimento())
                    .nacionalidade(hospede.getNacionalidade())
                    .telefone(hospede.getTelefone())
                    .reservas(hospede.getReservas())
                    .build());
    }

    public ResponseHospede updateHospede(Long idHospede, RequestHospede requestHospede){
        try{
            Hospede hospede = hospedeRepository.getReferenceById(idHospede);

            hospede.setNome(requestHospede.getNome());
            hospede.setSobrenome(requestHospede.getSobrenome());
            hospede.setDataNascimento(requestHospede.getDataNascimento());
            hospede.setNacionalidade(requestHospede.getNacionalidade());
            hospede.setTelefone(requestHospede.getTelefone());

            hospedeRepository.save(hospede);

            return new ResponseHospede(HospedeDto
                    .builder()
                    .id(hospede.getId())
                    .nome(hospede.getNome())
                    .sobrenome(hospede.getSobrenome())
                    .dataNascimento(hospede.getDataNascimento())
                    .nacionalidade(hospede.getNacionalidade())
                    .reservas(hospede.getReservas())
                    .build());

        }catch(EntityNotFoundException e) {
			throw new RuntimeException("Hospede com ID: " + idHospede + " não encontrado");
		}	

    }

    public void deleteHospede(Long idHospede){
        try {
			hospedeRepository.deleteById(idHospede);
		} catch (Exception e) {
			throw new ExceptinHandler("Hospede com ID: " + idHospede + " não encontrado");
		}
    }

    public ResponseHospede findByIdHospede(Long idHospede){
       Hospede hospede = hospedeRepository.findById(idHospede).map(a->a)
               .orElseThrow(() -> new EntityNotFoundException("A reserva ID:" + idHospede + " não existe."));

               return new ResponseHospede(HospedeDto
                .builder()
                .id(hospede.getId())
                .nome(hospede.getNome())
                .sobrenome(hospede.getSobrenome())
                .dataNascimento(hospede.getDataNascimento())
                .nacionalidade(hospede.getNacionalidade())
                .reservas(hospede.getReservas())
                .build());
    }

    public List<ResponseHospede> getAllHospede(){
        List<Hospede> hospedes = hospedeRepository.findAll();
        List<ResponseHospede> responseHospedes = new ArrayList<>();

        hospedes.forEach(hospede -> {
            ResponseHospede responseHospede = new ResponseHospede(HospedeDto
                    .builder()
                    .id(hospede.getId())
                    .nome(hospede.getNome())
                    .sobrenome(hospede.getSobrenome())
                    .dataNascimento(hospede.getDataNascimento())
                    .nacionalidade(hospede.getNacionalidade())
                    .reservas(hospede.getReservas())
                    .build());

            responseHospedes.add(responseHospede);
        });

        return responseHospedes;
    }
}
