/*
	 * 这个是解压ZIP格式文件的方法
	 *
	 * @zipFileName：是传进来你要解压的文件路径，包括文件的名字；
	 *
	 * @outputDirectory:选择你要保存的路劲；
	 */
	public static void unzip(String zipFileName, String outputDirectory) throws Exception {
		File desDir = new File(outputDirectory);
		if (!desDir.exists()) {
			desDir.mkdirs();
		}

		ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));
		ZipEntry z;
		String name = "";
		@SuppressWarnings("unused")
		String extractedFile = "";
		int counter = 0;

		while ((z = in.getNextEntry()) != null) {
			name = z.getName();
			Log.d(TAG, "unzipping file: " + name);
			if (z.isDirectory()) {
				Log.d(TAG, name + "is a folder");
				// get the folder name of the widget
				name = name.substring(0, name.length() - 1);
				File folder = new File(outputDirectory + File.separator + name);
				Log.d(TAG, "mkdir: " + outputDirectory + File.separator + name);
				folder.mkdirs();
				if (counter == 0) {
					extractedFile = folder.toString();
				}
				counter++;
			} else {
				Log.d(TAG, name + " is a normal file");
				File file = new File(outputDirectory + File.separator + name);
				File fileParentDir = file.getParentFile();
				if (!fileParentDir.exists()) {
					fileParentDir.mkdirs();
					Log.d("zheng", "mkdir:" + fileParentDir.getName());
				}
				Log.d(TAG, "createNewFile: " + outputDirectory + File.separator + name);

				file.createNewFile();
				// get the output stream of the file
				FileOutputStream out = new FileOutputStream(file);
				int ch;
				byte[] buffer = new byte[1024];
				// read (ch) bytes into buffer
				while ((ch = in.read(buffer)) != -1) {
					// write (ch) byte from buffer at the position 0
					out.write(buffer, 0, ch);
					out.flush();
				}
				out.close();
			}
		}
		in.close();
	}
