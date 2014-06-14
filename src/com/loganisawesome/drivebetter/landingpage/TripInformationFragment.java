package com.loganisawesome.drivebetter.landingpage;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.loganisawesome.drivebetter.R;
import com.loganisawesome.drivebetter.database.DatabaseManager;
import com.loganisawesome.drivebetter.database.models.TripModel;
import com.loganisawesome.drivebetter.database.objects.Trip;
import com.loganisawesome.drivebetter.utilities.asynctasks.SaveTripTask;
import com.loganisawesome.drivebetter.utilities.helpers.ClearFocusTouchListener;
import com.loganisawesome.drivebetter.utilities.helpers.ToastHelper;
import com.loganisawesome.drivebetter.utilities.interfaces.AsyncTaskCallbacks;
import com.loganisawesome.drivebetter.utilities.interfaces.ClearFocusInterface;
import com.loganisawesome.drivebetter.utilities.loaders.TripLoader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TripInformationFragment extends Fragment implements ClearFocusInterface, OnClickListener, AsyncTaskCallbacks, LoaderCallbacks<List<TripModel>>
{
	private static final int TRIP_LOADER_ID = 1;

	private EditText consumptionEditText;
	private EditText averageSpeedEditText;
	private EditText distanceEditText;
	// TODO Time picker dialog
	private EditText routeEditText;
	// private EditText notesEditText;

	private TableLayout tripTable;
	private List<TripModel> tripList = new ArrayList<>();

	private Button clearButton;
	private Button saveButton;

	private Double consumption;
	private Double avgSpeed;
	private Double distance;
	private Date arrivalTime;
	private String route;

	// private String notes;

	public static TripInformationFragment newInstance()
	{
		TripInformationFragment fragment = new TripInformationFragment();
		// set args here
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		initTripLoader();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.trip_information_fragment, container, false);

		// Inputs
		// Date picker
		this.consumptionEditText = (EditText) view.findViewById(R.id.consumptionEditText);
		this.averageSpeedEditText = (EditText) view.findViewById(R.id.averageSpeedEditText);
		this.distanceEditText = (EditText) view.findViewById(R.id.distanceEditText);
		// Time picker
		this.routeEditText = (EditText) view.findViewById(R.id.routeEditText);
		// this.notesEditText = (EditText) view.findViewById(R.id.notesEditText);

		// Buttons
		this.clearButton = (Button) view.findViewById(R.id.clearButton);
		this.clearButton.setOnClickListener(this);
		this.saveButton = (Button) view.findViewById(R.id.saveButton);
		this.saveButton.setOnClickListener(this);
		// TODO Will be removed
		Button clearDBButton = (Button) view.findViewById(R.id.clearDBButton);
		clearDBButton.setOnClickListener(this);

		// Tables
		TableLayout tripInputTableLayout = (TableLayout) view.findViewById(R.id.tripInputTableLayout);
		tripInputTableLayout.setShrinkAllColumns(true);
		tripInputTableLayout.setStretchAllColumns(true);
		this.tripTable = (TableLayout) view.findViewById(R.id.tripTableLayout);
		this.tripTable.setShrinkAllColumns(true);
		this.tripTable.setStretchAllColumns(true);

		populateTripTable();

		return view;
	}

	@Override
	public List<View> getViewsToClearFocus()
	{
		List<View> views = new ArrayList<>();
		views.add(this.consumptionEditText);
		views.add(this.averageSpeedEditText);
		views.add(this.distanceEditText);
		views.add(this.routeEditText);
		// views.add(this.notesEditText);
		return views;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.clearButton:
				clear();
				break;
			case R.id.saveButton:
				save();
				break;
			case R.id.clearDBButton:
				clearDB();
			default:
				break;
		}
	}

	// TODO Will be removed
	private void clearDB()
	{
		try
		{
			DatabaseManager.getInstance().deleteAll(Trip.class);
			refreshTrips();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initTripLoader()
	{
		getActivity().getSupportLoaderManager().initLoader(TRIP_LOADER_ID, null, this);
	}

	private void refreshTrips()
	{
		Loader<List<TripModel>> loader = getActivity().getSupportLoaderManager().getLoader(TRIP_LOADER_ID);
		if (loader != null)
		{
			loader.onContentChanged();
		}
		else
		{
			initTripLoader();
		}
	}

	private void clear()
	{
		this.consumptionEditText.setText(null);
		this.averageSpeedEditText.setText(null);
		this.distanceEditText.setText(null);
		// TODO Reset time
		this.routeEditText.setText(null);
		// this.notesEditText.setText(null);

		this.consumptionEditText.requestFocus();
	}

	private void save()
	{
		if (validate())
		{
			TripModel trip = new TripModel();
			trip.setConsumption(this.consumption);
			trip.setAvgSpeed(this.avgSpeed);
			trip.setDistance(this.distance);
			trip.setArrivalTime(this.arrivalTime);
			trip.setRoute(this.route);
			// trip.setNotes(this.notes);

			SaveTripTask task = new SaveTripTask(this, trip);
			task.execute();
		}
		else
		{
			// Tell me I'm retarded and need to put stuff in properly
			ToastHelper.showShortMessage(getActivity(), "Stop being retarded...");
		}
	}

	private Boolean validate()
	{
		// TODO do something smart with this
		Boolean valid =
			this.consumptionEditText.getText().toString() != "" &&
				this.averageSpeedEditText.getText().toString() != "" &&
				this.distanceEditText.getText().toString() != "" &&
				this.routeEditText.getText().toString() != "";

		if (valid)
		{
			// TODO get from time picker
			this.arrivalTime = Calendar.getInstance().getTime();
			this.route = this.routeEditText.getText().toString();
			// this.notes = this.notesEditText.getText().toString();

			try
			{
				this.consumption = Double.parseDouble(this.consumptionEditText.getText().toString());
				this.avgSpeed = Double.parseDouble(this.averageSpeedEditText.getText().toString());
				this.distance = Double.parseDouble(this.distanceEditText.getText().toString());
			}
			catch (Exception e)
			{
				valid = false;
			}
		}

		return valid;
	}

	private void populateTripTable()
	{
		if (this.tripTable != null)
		{
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

			int paddingLeftRight = (int) getActivity().getResources().getDimension(R.dimen.displayTablePaddingLeftRight);
			int paddingTopBottom = (int) getActivity().getResources().getDimension(R.dimen.displayTablePaddingTopBottom);

			// Leave header row
			this.tripTable.removeViews(1, this.tripTable.getChildCount() - 1);

			// TODO: No data row
			for (TripModel trip : this.tripList)
			{
				TableRow row = new TableRow(getActivity());
				TextView dateTextView = new TextView(getActivity());
				TextView consumptionTextView = new TextView(getActivity());
				TextView avgSpeedTextView = new TextView(getActivity());
				TextView distanceTextView = new TextView(getActivity());
				TextView timeTextView = new TextView(getActivity());
				TextView routeTextView = new TextView(getActivity());
				// TextView notesTextView = new TextView(getActivity());

				dateTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				consumptionTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				avgSpeedTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				distanceTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				timeTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				routeTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
				// notesTextView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);

				dateTextView.setText(dateFormat.format(trip.getArrivalTime()));
				consumptionTextView.setText(decimalFormat.format(trip.getConsumption()));
				avgSpeedTextView.setText(decimalFormat.format(trip.getAvgSpeed()));
				distanceTextView.setText(decimalFormat.format(trip.getDistance()));
				timeTextView.setText(timeFormat.format(trip.getArrivalTime()));
				routeTextView.setText(trip.getRoute());
				// notesTextView.setText(trip.getNotes());

				row.addView(dateTextView);
				row.addView(consumptionTextView);
				row.addView(avgSpeedTextView);
				row.addView(distanceTextView);
				row.addView(timeTextView);
				row.addView(routeTextView);
				// row.addView(notesTextView);

				this.tripTable.addView(row);
			}
		}
	}

	@Override
	public void onTaskComplete(Boolean success)
	{
		ToastHelper.showLongMessage(getActivity(), "Saved successfully: " + success);
		if (success)
		{
			ClearFocusTouchListener.hideKeyboard(getActivity(), this.saveButton.getWindowToken());
			refreshTrips();
			clear();
		}
	}

	@Override
	public Loader<List<TripModel>> onCreateLoader(int loaderId, Bundle args)
	{
		return new TripLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<TripModel>> loader, List<TripModel> tripList)
	{
		this.tripList = tripList;
		populateTripTable();
	}

	@Override
	public void onLoaderReset(Loader<List<TripModel>> loader)
	{
	}
}
