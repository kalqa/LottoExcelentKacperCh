package pl.lotto.numberreceiver;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    public void should_return_lottery_id_and_draw_date_when_user_gave_six_numbers_in_range_of_1_99() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNotNull();
        assertThat(result.lotteryId()).isNotEqualTo(null);
        assertThat(result.message().get(0)).isEqualTo("All went good");
    }

    @Test
    public void should_return_result_without_lottery_id_and_draw_date_when_user_gave_six_numbers_out_range_of_1_99() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 0);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNull();
        assertThat(result.lotteryId()).isNull();
        assertThat(result.message().get(0)).isEqualTo("Number from outside the range");
    }

    @Test
    public void should_return_result_without_lottery_id_and_draw_date_when_user_gave_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNull();
        assertThat(result.lotteryId()).isNull();
        assertThat(result.message().get(0)).isEqualTo("You gave less than six numbers");
    }

    @Test
    public void should_return_result_without_lottery_id_and_draw_date_when_user_gave_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6, 7);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNull();
        assertThat(result.lotteryId()).isNull();
        assertThat(result.message().get(0)).isEqualTo("You gave more than six numbers");
    }

    @Test
    public void should_return_result_without_lottery_id_and_draw_date_when_user_gave_at_least_one_duplicate() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 5);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNull();
        assertThat(result.lotteryId()).isNull();
        assertThat(result.message().get(0)).isEqualTo("Your numbers have duplicate");
    }


    @Test
    public void should_return_result_without_lottery_id_and_draw_date_when_user_make_two_mistakes() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createForTests();
        List<Integer> numbersFromUser = List.of(1, 2, 1000, 4, 5, 5);
        // when
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.drawDate()).isNull();
        assertThat(result.lotteryId()).isNull();
        assertThat(result.message().get(0)).isEqualTo("Number from outside the range");
        assertThat(result.message().get(1)).isEqualTo("Your numbers have duplicate");
    }
}
