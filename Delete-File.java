// 删除文件的方法
	public static void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete();
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (File file2 : files) { // 遍历目录下所有的文件
					deleteFile(file2);
				}
			}
			file.delete();
		} else {
			Log.d("zheng", "文件不存在！" + "\n");
		}
	}
