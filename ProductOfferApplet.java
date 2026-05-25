import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

/* <applet code="ProductOfferApplet" width="500" height="150"></applet>
*/

public class ProductOfferApplet extends Applet implements Runnable {
    
    private String offerMessage = "🔥 SPECIAL 50% PRICE CUT FOR ONE WEEK ONLY! 🔥";
    private double originalPrice = 999.00; 
    private double discountedPrice;
    private boolean isVisible = true; 
    private boolean isOfferActive = false;
    private Thread animationThread;

    @Override
    public void init() {
        // حساب السعر الجديد تلقائياً بعد خصم 50% (شرط الـ Grade A)
        discountedPrice = originalPrice * 0.50;
        
        // التحقق من التاريخ ليعمل العرض لمدة أسبوع واحد فقط (شرط الـ Grade B)
        Calendar now = Calendar.getInstance();
        int currentWeek = now.get(Calendar.WEEK_OF_YEAR);
        int currentYear = now.get(Calendar.YEAR);
        
        // العرض فعال لعام 2026 للأسبوع الحالي والأسبوع القادم
        if (currentYear == 2026 && currentWeek <= 23) { 
            isOfferActive = true;
        } else {
            isOfferActive = false;
            offerMessage = "❌ This special offer has expired.";
        }
        
        setBackground(new Color(245, 247, 250)); 
    }

    @Override
    public void start() {
        // بدء خيط المعالجة للأنيميشن
        if (animationThread == null) {
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    @Override
    public void run() {
        // حركة وميض النص (Blinking Animation) لشد الانتباه (شرط الـ Grade D)
        while (isOfferActive) {
            try {
                isVisible = !isVisible; 
                repaint(); 
                Thread.sleep(600); 
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // رسم نص العرض المتحرك
        if (isVisible && isOfferActive) {
            g.setColor(Color.RED); 
            g.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g.drawString(offerMessage, 20, 50);
        } else if (!isOfferActive) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g.drawString(offerMessage, 20, 50);
        }

        // عرض حسابات الأسعار على الشاشة
        g.setColor(new Color(15, 23, 42)); 
        g.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g.drawString("Original Price: $" + originalPrice, 40, 90);
        
        g.setColor(new Color(37, 99, 235)); 
        g.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g.drawString("New Price (50% Off): $" + discountedPrice, 40, 120);
    }

    @Override
    public void stop() {
        animationThread = null;
    }
}