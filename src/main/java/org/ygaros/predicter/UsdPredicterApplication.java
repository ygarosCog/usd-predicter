package org.ygaros.predicter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class UsdPredicterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsdPredicterApplication.class, args);

	}
	public static void codeBaseForGenerationOutputFromModel(){
		SavedModelBundle modelTrained = SavedModelBundle.load("modelTrained");

		Map<String, Tensor> map = new HashMap<>();
		Tensor t = Tensor.of(TFloat32.class, Shape.of(1, 4),
				(ten) -> ten
						.setFloat(10.0f, 0L, 0L)
						.setFloat(20.0f, 0L, 1L)
						.setFloat(4.73f, 0L, 2L)
						.setFloat(4.75f, 0L, 3L)

		);
		Tensor t1 = Tensor.of(TFloat32.class, Shape.of(1, 2));

		map.put("sequential_input", t);
		Map<String, Tensor> call = modelTrained.call(map);

		call.forEach((k, v) -> System.out.println(v.asRawTensor().data().asFloats().getFloat(0) + "        " + v.asRawTensor().data().asFloats().getFloat(1)));

	}
}



