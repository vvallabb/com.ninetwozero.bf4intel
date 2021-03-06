package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.net.URL;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Transaction;

public abstract class BaseDaoService<T extends Model, E extends RefreshCompletedEvent> extends BaseApiService {
    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        if (intentCount == 1) {
            Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<Boolean>(
                    getUrlForService(),
                    this
                ) {
                    @Override
                    protected Boolean doParse(String json) {
                        final Transaction transaction = new Transaction();
                        boolean success = true;

                        final T daoObject = parseJsonIntoDao(json);
                        if (!daoObject.save(transaction)) {
                            success = false;
                        }

                        transaction.setSuccessful(success);
                        transaction.finish();
                        return success;
                    }

                    @Override
                    protected void deliverResponse(Boolean result) {
                        final E event = getEventToBroadcast(result);
                        BusProvider.getInstance().post(event);
                        stopSelf();
                    }
                }
            );
        }
        return Service.START_NOT_STICKY;
    }


    protected abstract T parseJsonIntoDao(String json);

    protected abstract URL getUrlForService();

    protected abstract E getEventToBroadcast(boolean result);
}
