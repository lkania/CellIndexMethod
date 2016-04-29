package itba.edu.ar.input.file;

import java.util.List;

import itba.edu.ar.input.file.data.Data;

public abstract class FileGenerator {

	protected static int getTotalParticleQuantity(List<Data> staticFileDatas) {
		int ans = 0;
		for (Data data : staticFileDatas)
			ans += data.getParticleQuantity();

		return ans;
	}
	
}
