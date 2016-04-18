package itba.edu.ar.input.file;

import java.util.List;

import itba.edu.ar.input.file.data.StaticFileData;

public abstract class FileGenerator {

	protected static int getTotalParticleQuantity(List<StaticFileData> staticFileDatas) {
		int ans = 0;
		for (StaticFileData data : staticFileDatas)
			ans += data.getParticleQuantity();

		return ans;
	}
	
}
