package sd.rittal.cardsmodule.uitl;

import android.content.Context;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RittalService {


    /**
     * <p>
     * This method execute checkOutIDRequest operations , just call it from an Activity or a fragment
     * <p>
     * example :
     * <p>
     * CallRittalService.cardTransfer(getApplicationContext(), "RittalTest", "PTest_Rittal", "RittalPgApp", "https://192.168.1.1");
     * </p>
     *
     * @param context    context.
     * @param orgUsrName organization name
     * @param appName    application name
     * @param orgPass    organization password
     * @param baseUrl    baseUrl its given from service provider
     * @param amount     amount of transactions
     * @param toPan      Recipient card
     */

    public static void checkOutIDRequest(Context context, String orgUsrName, String orgPass, String appName, String amount, String toPan, String baseUrl) {
        Intent intent = null;
        try {
            intent = new Intent(context,
                    Class.forName("sd.rittal.cardsmodule.CardTransferActivity"));

            intent.putExtra("orgUsrName", orgUsrName);
            intent.putExtra("orgPass", orgPass);
            intent.putExtra("appName", appName);
            intent.putExtra("amount", amount);
            intent.putExtra("baseUrl", baseUrl);
            intent.putExtra("toPan", toPan);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
