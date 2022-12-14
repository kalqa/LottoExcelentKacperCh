package pl.lotto.numbergenerator;

import pl.lotto.numberreceiver.LotteryTicket;
import pl.lotto.numberreceiver.NumberReceiverRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryNumberGeneratorRepository implements NumberGeneratorRepository {

    List<DrawnNumbers> drawnNumbersDB = new ArrayList<>();

    @Override
    public DrawnNumbers save(DrawnNumbers drawnNumbers) {
        drawnNumbersDB.add(drawnNumbers);
        return drawnNumbers;
    }

    @Override
    public DrawnNumbers findDrawnNumbersByDrawDate(LocalDateTime drawDate) {
            List<DrawnNumbers> filteredDrawNumbers = drawnNumbersDB.stream()
                .filter(drawnNumbers -> drawnNumbers.drawDate().equals(drawDate))
                .collect(Collectors.toList());
            if(filteredDrawNumbers.size() == 1) {
                return filteredDrawNumbers.get(0);
            }
            return null;
    }
}
