package br.com.fatec.fatecsjc.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.fatec.fatecsjc.R;

public class RequerimentoList extends ArrayAdapter<Requerimento> {

    private Activity context;
    private List<Requerimento> requerimentoList;

    public RequerimentoList(Activity context, List<Requerimento> requerimentoList) {
        super(context, R.layout.list_req_layout, requerimentoList);
        this.context = context;
        this.requerimentoList = requerimentoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_req_layout, null, true);
        TextView textViewTipo = (TextView) listViewItem.findViewById(R.id.textViewTipo);
        TextView textViewIdentificacao = (TextView) listViewItem.findViewById(R.id.textViewIdentificacao);
        TextView textViewComplemento = (TextView) listViewItem.findViewById(R.id.textViewComplemento);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.textViewData);
        TextView textViewStatus = (TextView) listViewItem.findViewById(R.id.textViewStatus);
        Requerimento requerimento = requerimentoList.get(position);
        textViewTipo.setText(requerimento.getTipo());
        textViewIdentificacao.setText(requerimento.getIdentificacao());
        textViewComplemento.setText(requerimento.getComplemento());
        textViewData.setText(requerimento.getData());
        textViewStatus.setText(requerimento.getStatus());
        return listViewItem;
    }
}