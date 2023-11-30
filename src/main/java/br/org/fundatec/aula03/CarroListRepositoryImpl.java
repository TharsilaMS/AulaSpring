package br.org.fundatec.aula03;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class CarroListRepositoryImpl implements CarroRepository {
    private List<Carro> carros = new ArrayList<>();

    @Override
    public List<Carro> listAllCarros() {
        return carros;
    }

    @Override
    public void saveCarro(Carro carro) {
        this.carros.add(carro);
    }

    @Override
    public void deleteCarro(String placa) {
//        int posicao = -1;
//        for(int index = 0; index <= carros.size(); index++) {
//            if(carros.get(index).getPlaca().equals(placa)) {
//                posicao = index;
//                break;
//            }
//
//        }
//
//        if(posicao == -1) {
//            throw new RuntimeException("Carro não localizado");
//        }

//        carros.remove(posicao);

        // com stream

        carros.stream()
                .filter(carro -> carro.getPlaca().equals(placa))
                .findFirst()
                .map(carros::remove)
                .orElseThrow(() -> new RuntimeException("Carro não localizado"));


        // primeira forma
//        if(carroOptional.isPresent()) {
//            Carro carro = carroOptional.get();
//            carros.remove(carro);
//        } else {
//            throw new RuntimeException("Carro não localizado");
//        }

//        carroOptional.map(carros::remove)
//             .orElseThrow(() -> new RuntimeException("Carro não localizado"));


    }

    @Override
    public void editCarro(String codigoPlaca,
                          Carro carro) {

//        carro.setPlaca(codigoPlaca);
//
//        if(!carros.contains(carro)) {
//            throw new RuntimeException("Carro não existe");
//        }

        carros.stream()
                .filter(localizado -> localizado.getPlaca().equals(codigoPlaca))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Carro não existe"));

        carros = carros.stream()
                .filter(localizado -> !localizado.getPlaca().equals(codigoPlaca))
                .collect(Collectors.toList());

        carro.setPlaca(codigoPlaca);
        carros.add(carro);

    }

}
