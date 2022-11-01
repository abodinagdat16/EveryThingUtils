//the class package

package com.android.prime.arab.ware.everythingutils;

//the classes imports

import android.graphics.Bitmap; /*the Bitmap class which is the MAIN thing that draw and get info about images ! */
import android.graphics.pdf.PdfDocument; /*pdf creator , and more */
import android.os.Handler; /*the thing that is used to make something work on the app ui*/
import android.os.Looper;/*the thing that is used as param in the Handler constructor*/
import android.os.ParcelFileDescriptor;/*the thing that is used to get the pdf file*/
import java.io.File;/*the file class which is used to get info from a file , it is not recommend by google , but still work (until android 13 , i hope it will still supported in 14 and more)*/
import android.content.Context;/*the context of an activity , fragment etc...*/
import java.io.FileOutputStream;/*it saves a fil*/
import java.io.IOException;/*it gets a file task error*/
import java.io.InputStream;/*it helps in file managing , and more*/
import java.util.ArrayList;/*it is a list :D*/
import android.graphics.Bitmap.Config;


//the brain of this class

public class PdfUtils {
	
	//the field of this class
	
	private android.graphics.pdf.PdfRenderer PR;
	private android.graphics.pdf.PdfRenderer.Page Rpage;
	private Bitmap Bitmap;
	private java.io.File FE;
	private java.io.InputStream asset;
	private java.io.FileOutputStream output;
	private byte[] buffer;
	private int Psize;
	private Bitmap bit;
	private Bitmap bitmap;
	private float bitmapRatio;
	private int width;
	private int height;
	private int maxSize;
	public android.graphics.pdf.PdfDocument.PageInfo myPageInfo;
	public android.graphics.pdf.PdfDocument.Page page;
	private java.io.File file;
	private Context context;
	private ArrayList<Bitmap> bitmaps;
	//the interface of this class
	
	public interface PdfLoading {
		public void done();
		public void loading();
		public void error(String s);
	}
	
	//the constructor of this class
	
	public PdfUtils(Context c) {
		context = c;
	}
	
	//the methods of this class
	
	public void setFromFile(File f , PdfLoading pl) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							if(pl != null) {
								pl.loading();
							}
						}
					});
					
					
					
					
					
					
					PR = new android.graphics.pdf.PdfRenderer(new ParcelFileDescriptor(ParcelFileDescriptor.open(f, ParcelFileDescriptor.MODE_READ_ONLY)));
					
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							if(pl != null) {
								pl.done();
							}
						}
					});
					
					
					
					} catch (Exception e){
						
						new Handler(Looper.getMainLooper()).post(new Runnable() {
							@Override
							public void run() {
								if(pl != null) {
									pl.error(e.getMessage().toString());
								}
							}
						});
						
					
				}
			}
		}).start();
		
	}
	
	public void setFromAssets(String assets , PdfLoading pl) {
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
			
		
		try {
			
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					if(pl != null) {
						pl.loading();
					}
				}
			});
			
			asset = context.getAssets().open(assets);
			output = null;
			File myFile = new File(context.getCacheDir(),"tempPdf.pdf");
			output = new java.io.FileOutputStream(myFile);
			buffer = new byte[1024];
			while ((Psize = asset.read(buffer)) != -1) {
				output.write(buffer, 0, Psize);
			}
			asset.close();
			output.close();
			
			setFromFile(myFile,new PdfLoading() {
				
				@Override
				public void error(String s) {
					if(pl!= null) {
						new Handler(Looper.getMainLooper()).post(new Runnable() {
							@Override
							public void run() {
								if(pl != null) {
									pl.error(s);
								}
							}
						});
					}
				}

				@Override
				public void loading() {
					
				}

				@Override
				public void done() {
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							if(pl != null) {
								pl.done();
							}
						}
					});
				}

			});
			
			} catch (java.io.IOException e) {
				if(pl != null) {
					pl.error(e.getMessage().toString());
				}
		}
		
		}
}).start();
		
	}
	
	private void setFromAssets2(InputStream assets , PdfLoading pl) {
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				
				
				try {
					
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							if(pl != null) {
								pl.loading();
							}
						}
					});
					
					output = null;
					File myFile = new File(context.getCacheDir(),"tempPdf.pdf");
					output = new java.io.FileOutputStream(myFile);
					buffer = new byte[1024];
					while ((Psize = assets.read(buffer)) != -1) {
						output.write(buffer, 0, Psize);
					}
					assets.close();
					output.close();
					
					setFromFile(myFile,new PdfLoading() {
						
						@Override
						public void error(String s) {
							if(pl!= null) {
								new Handler(Looper.getMainLooper()).post(new Runnable() {
									@Override
									public void run() {
										if(pl != null) {
											pl.error(s);
										}
									}
								});
							}
						}
						
						@Override
						public void loading() {
							
						}
						
						@Override
						public void done() {
							new Handler(Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if(pl != null) {
										pl.done();
									}
								}
							});
						}
						
					});
					
					} catch (java.io.IOException e) {
					if(pl != null) {
						pl.error(e.getMessage().toString());
					}
				}
				
			}
		}).start();
		
	}
	
	
	public void setFromResources(int res , PdfLoading pl) {
		try {
			asset = context.getResources().openRawResource(res);
			setFromAssets2(asset,pl);
			} catch (Exception e) {
			if(pl != null) {
				pl.error(e.getMessage().toString());
			}
		}
	}
	
	public void setFromResources(String folder , String name , PdfLoading pl) {
		setFromResources(context.getResources().getIdentifier(name,folder,context.getPackageName()),pl);
	}
	
	public void setFromInputStream(InputStream is , PdfLoading pl) {
		setFromAssets2(is,pl);
	}
	
	public Bitmap getPage(int page) {
		
		Rpage = PR.openPage(page);
		
		Bitmap b = Bitmap.createBitmap(Rpage.getWidth(),Rpage.getHeight(),Config.ARGB_8888);
		
		Rpage.render(b, null, null, android.graphics.pdf.PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
		
		Rpage.close();
		
		return b;
	}
	
	//i dont recommend using this , for big files
	//it can throw OutOfMemory Error
	
	public ArrayList<Bitmap> getPages() {
		for(int a = 0; a < getPagesCount(); a++) {
			bitmaps.add(getPage(a));
		}
		return bitmaps;
	}
	
	public int getPagesCount() {
		return PR.getPageCount();
	}
	
	public int getPageWidth(int page) {
		return getPage(page).getWidth();
	}
	
	public int getPageHeight(int page) {
		return getPage(page).getHeight();
	}
	
	public void compresssPdf(int percent , String saveTo , PdfLoading pl) {
		
		if(pl != null) {
			pl.loading();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
						for(int a = 0; a < getPages().size(); a++) {
							bitmaps.set(a,Bitmap.createScaledBitmap(getPage(a),(getPageWidth(a)/(percent/100)),(getPageHeight(a)/(percent/100)),true));
						}
						PdfCreator pc = new PdfCreator(context , saveTo);
						pc.setPdf(PdfUtils.this);
						pc.save2(pl);
					
					} catch(Exception e) {
					if(pl != null) {
						new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
							@Override
							public void run() {
								if(pl != null) {
									pl.error(e.getMessage().toString());
								}
							}
						});
					}
				}
			}
		}).start();
	
		
		
		
		
		
	}
	
	
	
	public static class PdfCreator {
		
		public ArrayList<Bitmap> bitms;
		public android.graphics.pdf.PdfDocument pd;
		public String path;
		public android.graphics.pdf.PdfDocument.PageInfo pageInfo2;
		public android.graphics.pdf.PdfDocument.Page page2;
		
		public Context context;

		
		public PdfCreator(Context c , String whereToSave) {
			pd = new android.graphics.pdf.PdfDocument();
			context = c;
			path = whereToSave;
			bitms = new ArrayList<>();
		}
		
		public void setPdf(PdfUtils pu) {
			pd = new android.graphics.pdf.PdfDocument();
			bitms = new ArrayList<>();
			bitms.addAll(pu.getPages());
		}
		
		public void addPdf(PdfUtils pu) {
			bitms.addAll(pu.getPages());
		}
		
		public void addPdfAtFirst(PdfUtils pu) {
			addTemporary(pu.getPages(),bitms);
		}
		
		private void addTemporary(ArrayList<Bitmap> b , ArrayList<Bitmap> b2) {
			b.addAll(b2);
			b2 = new ArrayList<>();
			b2.addAll(b);
		}
		
		public void setBitmaps(ArrayList<Bitmap> bits) {
			bitms = bits;
		}
		
		public void addBitmapsAtLast(ArrayList<Bitmap> bits) {
			bitms.addAll(bits);
		}
		
		public void addBitmapsAtFirst(ArrayList<Bitmap> bits) {
			bits.addAll(bitms);
			bitms = bits;
		}
		
		public void addPage(Bitmap b) {
			bitms.add(b);
		}
		
		public void addPageAt(Bitmap b , int page) {
			bitms.add(page,b);
		}
		
		public void removePage(int page) {
			bitms.remove(page);
		}
		
		public void setPage(Bitmap b , int page) {
			bitms.set(page,b);
		}
		
		public int getPagesCount() {
			return bitms.size();
		}
		
		public int getPageWidth(int page) {
			return bitms.get(page).getWidth();
		}
		
		public int getPageHeight(int page) {
			return bitms.get(page).getHeight();
		}
		
		public ArrayList<Bitmap> getPages() {
			return bitms;
		}
		
		public Bitmap getPage(int page) {
			return bitms.get(page);
		}
		
		public void save(PdfLoading pl) {
			
			
			
			
			
			if(pl != null) {
				pl.loading();
			}
			
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					for(int a = 0; a < bitms.size(); a++) {
						pageInfo2 = new android.graphics.pdf.PdfDocument.PageInfo.Builder(getPageWidth(a),getPageHeight(a),1).create();
						page2 = pd.startPage(pageInfo2);
						page2.getCanvas().drawBitmap(bitms.get(a).copy(bitms.get(a).getConfig(),true),0,0, null);
						pd.finishPage(page2);
					}
					
					
					try {
						pd.writeTo(new FileOutputStream(new File(path)));
						
						if(pl != null) {
							new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if(pl != null) {
										pl.done();
									}
								}
							});
						}
						
					} catch(IOException e) {
						if(pl != null) {
							new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if(pl != null) {
										pl.error(e.getMessage().toString());
									}
								}
							});
						}
					}
					
					
					
				}
			}).start();
		}
		
		
		public void save2(PdfLoading pl) {
			
			
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					for(int a = 0; a < bitms.size(); a++) {
						pageInfo2 = new android.graphics.pdf.PdfDocument.PageInfo.Builder(getPageWidth(a),getPageHeight(a),1).create();
						page2 = pd.startPage(pageInfo2);
						page2.getCanvas().drawBitmap(bitms.get(a).copy(bitms.get(a).getConfig(),true),0,0, null);
						pd.finishPage(page2);
					}
					
					
					try {
						pd.writeTo(new FileOutputStream(new File(path)));
						
						if(pl != null) {
							new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if(pl != null) {
										pl.done();
									}
								}
							});
						}
						
						} catch(IOException e) {
						if(pl != null) {
							new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if(pl != null) {
										pl.error(e.getMessage().toString());
									}
								}
							});
						}
					}
					
					
					
				}
			}).start();
		
		}
		
		
	}
	
	
	
    
}
