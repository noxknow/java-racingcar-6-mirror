package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.handler.ErrorHandler;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RacingCarTest {

    @DisplayName("자동차 이름이 영어와 한글이 아닌 경우 예외가 발생한다")
    @ParameterizedTest(name = "[{index}] input : {0} ")
    @ValueSource(strings = {"poB1,현대,woni", "pobi,현대1,woni"})
    void createCarNamesWithInvalidWord(String inputString) {
        assertThatThrownBy(() -> new RacingCar(List.of(inputString.split(","))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_ENGLISH_KOREAN.getException().getMessage());
    }

    @DisplayName("자동차 이름이 중복된 경우 예외가 발생한다")
    @ParameterizedTest(name = "[{index}] input : {0} ")
    @ValueSource(strings = {"pobi,pobi,woni", "pobi,현대,woni,현대"})
    void createCarNamesWithDuplicate(String inputString) {
        assertThatThrownBy(() -> new RacingCar(List.of(inputString.split(","))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.DUPLICATE_NUMBER.getException().getMessage());
    }
}
