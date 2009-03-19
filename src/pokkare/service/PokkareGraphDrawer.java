package pokkare.service;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.FontMetrics;

import pokkare.model.Games;
import pokkare.model.Player;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder; 

public class PokkareGraphDrawer {

	private int imageWidth = 75;
	private int imageHeight = 150;

	private int verticalInset = 25;
	private int horizontalInset = 25;
	private int notchLength = 7;

	private Line2D.Double verticalAxis;
	private Line2D.Double horizontalAxis;
	private Graphics2D graphics;

	private int multiplier = 1;
	private int maxPoints = 0;

	EventService event = new EventService();
	PointsService points = new PointsService();

	public String createImage(OutputStream stream) throws IOException {
		ArrayList<Games> games = (ArrayList<Games>)event.findGames();
		ArrayList<Player> players = (ArrayList<Player>)event.findPlayers();

		int horizontalNotches = games.size();

		System.out.println("horizontalNotches: " + horizontalNotches);

		imageWidth = imageWidth * horizontalNotches * multiplier;
		imageHeight = imageHeight * multiplier;
		verticalInset = verticalInset * multiplier;
		horizontalInset = horizontalInset * multiplier;
		notchLength = notchLength * multiplier;

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
		if(encoder == null){
			throw new IllegalStateException("JPEGImageEncoder encoder was null in PokkareGraphDrawer.createImage()");
		}
		
		BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_INDEXED);
		if (bi == null){
			throw new IllegalStateException("BufferedImage bi was null in PokkareGraphDrawer.createImage()");
		}
		
		graphics = bi.createGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		graphics.setColor(Color.red);

		//draw vertical axis
		verticalAxis = new Line2D.Double(horizontalInset, verticalInset, horizontalInset, imageHeight - verticalInset);
		graphics.draw(verticalAxis);

		//draw horizontal axis
		horizontalAxis = new Line2D.Double(horizontalInset, imageHeight - verticalInset, imageWidth - horizontalInset, imageHeight - verticalInset);
		graphics.draw(horizontalAxis);

		//draw notches for vertical axis
		//check for and utilize scale
		//maxPoints is set in calling class
		if (maxPoints < 10) maxPoints = 10;

		int verticalNotches = maxPoints / 10;

		double verticalInterval = (verticalAxis.y2 - verticalInset - 20) / verticalNotches;

		for (int i = 0; i < verticalNotches; ++i) {

			Line2D.Double verticalNotch = new Line2D.Double(verticalAxis.getP1().getX() -  notchLength/2, 
					verticalAxis.getP2().getY() - ((i + 1) * verticalInterval), 
					verticalAxis.getP1().getX() + notchLength/2, 
					verticalAxis.getP2().getY() - ((i + 1) * verticalInterval));
			/*
		    	Line2D.Double verticalNotch = new Line2D.Double(verticalAxis.getP1().getY() + ((i + 1) * verticalInterval), 
                        verticalAxis.getP1().getX() - notchLength/ 2, 
                        verticalAxis.getP1().getY() + ((i + 1) * verticalInterval), 
                        verticalAxis.getP1().getX() + notchLength/2);

			 */

			System.out.println("drawing vertical notch");
			graphics.draw(verticalNotch);

			String scale = "" + ((i + 1) * 10);

			//draw numbers for scale
			FontMetrics metrics = graphics.getFontMetrics();
			int scaleWidthX = metrics.stringWidth(scale);
			int scaleHeightY = metrics.getAscent()/2;

			Double stringPositionX = verticalNotch.getP1().getX() - scaleWidthX - (multiplier * 5);
			Double stringPositionY = verticalNotch.getP1().getY() + scaleHeightY;

			graphics.drawString(scale, stringPositionX.intValue(), stringPositionY.intValue());


		}


		//draw notches for horizontal axis

		//horisontaalisia notcheja on yht� paljon kuin pelej�. jokaiselle pelille varataan tilaa
		double horizontalInterval = (horizontalAxis.x2 - horizontalInset - 20) / horizontalNotches;


		for (int i = 0; i < horizontalNotches; ++i) {

			Line2D.Double horizontalNotch = new Line2D.Double(horizontalAxis.getP1().getX() + ((i + 1) * horizontalInterval), 
					horizontalAxis.getP1().getY() - notchLength/2, 
					horizontalAxis.getP1().getX() + ((i + 1) * horizontalInterval), 
					horizontalAxis.getP1().getY() + notchLength/2);

			System.out.println("drawing horizontal notch");
			graphics.draw(horizontalNotch);

			//TODO: puukota t�h�n joskus simpledate jne. muista ett� kuvan t�ytyy olla tarpeeksi leve�
			//draw dates for horizontal axis' notches

			SimpleDateFormat formatter = new SimpleDateFormat("d.M. yyyy");
			String gameDate = formatter.format(games.get(i).getGameDate()) + " #" + games.get(i).getGameNumber();

			FontMetrics metrics = graphics.getFontMetrics();
			int scaleWidthX = metrics.stringWidth(gameDate);
			int scaleHeightY = metrics.getAscent()/2;

			Double stringPositionX = horizontalNotch.getP1().getX() - scaleWidthX/2 ;
			Double stringPositionY = horizontalNotch.getP1().getY() + scaleHeightY + (multiplier * 17);

			graphics.drawString(gameDate, stringPositionX.intValue(), stringPositionY.intValue());

			//get result data from EventService

			//for each player
			//get games
			//get scores for games
			//draw graphs for scores

			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

			for (int ii = 0; ii < players.size(); ++ii) {

				System.out.println("luupaako kah");
				Player player = players.get(ii);

				Double startPointX = verticalAxis.getP2().getX();
				Double startPointY = verticalAxis.getP2().getY();

				for (int jj = 0; jj < games.size(); ++jj) {

					System.out.println("loop");
					Integer score = event.findScoreForGameAndPlayer(games.get(jj).getId(), player.getId());

					if (map.get(player.getId()) == null) {
						map.put(player.getId(), score);
					}
					else {
						map.put(player.getId(), score + map.get(player.getId()));
					}

					//now we have the score for this player for this game
					//let's draw it
					Double endPointX = startPointX + horizontalInterval;
					Double endPointY = 0.0;
					if (score == 0) {
						endPointY = startPointY;
					}
					else { 
						endPointY = startPointY - (verticalInterval / 10) * score; 	
					}

					Line2D line = new Line2D.Double(startPointX, startPointY, endPointX, endPointY);

					System.out.println("drawing graph for: " + player.getName() + " startX: " + startPointX + " startY: " + startPointY + " endX: " + endPointX + " endY: " + endPointY + " score was: " + score + " and game date was: " + games.get(jj).getGameDate());
					graphics.draw(line);

					//draw player name next to line if we're on the last leg of the loop
					//TODO: find a way to not make player names overlap, 
					//maybe an arraylist with stringposition and compare it to
					//the others' metrics.getascent
					if (jj + 1 == games.size()) {
						graphics.drawString(player.getName() + ": " + map.get(player.getId()), endPointX.intValue() + multiplier * 5, endPointY.intValue() + metrics.getAscent()/2);
					}
					startPointX = endPointX;
					startPointY = endPointY;
				}

			}



		}




		encoder.encode(bi);

		return "image/jpg";
	}

	public static void main(String[] args)
	{

		try
		{
			FileOutputStream f = new FileOutputStream("pokkaregraph.jpg");
			PokkareGraphDrawer drawer = new PokkareGraphDrawer();
			drawer.setMultiplier(2);
			drawer.createImage(f);
			f.close(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int getMaxPoints(){
		return this.maxPoints;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getMultiplier(){
		return this.multiplier;
	}
}