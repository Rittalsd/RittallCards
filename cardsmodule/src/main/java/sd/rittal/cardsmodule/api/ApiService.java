package sd.rittal.cardsmodule.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;
import sd.rittal.cardsmodule.beans.CardTransferRequest;
import sd.rittal.cardsmodule.beans.CardTransferResponse;
import sd.rittal.cardsmodule.beans.CheckOutIDRequest;
import sd.rittal.cardsmodule.beans.CheckOutIDResponse;
import sd.rittal.cardsmodule.beans.PaymentInfoRequest;
import sd.rittal.cardsmodule.beans.PaymentInfoResponse;
import sd.rittal.cardsmodule.beans.PinPublicKeyRequest;
import sd.rittal.cardsmodule.beans.PinPublicKeyResponse;

public interface ApiService {


    @POST()
    Call<PaymentInfoResponse> getPaymentInfo(@Url String url, @Body PaymentInfoRequest paymentInfoRequest);


    @POST()
    Call<PinPublicKeyResponse> getPublicKey(@Url String url, @Body PinPublicKeyRequest keyBody);


    @POST()
    Call<CheckOutIDResponse> checkOutIDRequest(@Url String url,@Body CheckOutIDRequest checkOutIDRequest);

    // "checkOutID": "01a39a8e-3b00-4bfd-8488-6c332e2f7be6",

    @POST()
    Call<CardTransferResponse> cardTransfer(@Url String url, @Body CardTransferRequest transferRequest);

    // -----> > > >
}
