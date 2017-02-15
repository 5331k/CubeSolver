package org.khan.solver.cubesolver.writer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PuzzlePieceFileWriter extends AbstractWriter{

	public final String OUTPUT_DIR = "output";
	String fileName;
	FileWriter fileWriter;
	
	public PuzzlePieceFileWriter(){
		this.fileName = new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
	}
	
	public PuzzlePieceFileWriter(String fileName) {
		this.fileName = fileName;
	}
	
	public String getDirPath(){
		File file = new File(System.getProperty("user.dir")+"/"+OUTPUT_DIR);
		if(!file.exists()){
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	@Override
	public void printMatrix(boolean[][] matrix, int rowSize, int colsize, Map<String, String> valueMap) {
		try {
			
			File file = new File(getDirPath()+"/"+fileName);
			fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);
			
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < colsize; j++) {
					Boolean val = matrix[i][j];
					String value = valueMap.get(val.toString());
					output.write(value);
				}
				 output.newLine();
			}
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
