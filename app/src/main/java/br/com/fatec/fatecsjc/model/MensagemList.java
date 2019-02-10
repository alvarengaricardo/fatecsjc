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

/**
 * Created by ricardo on 23/08/2017.
 */

public class MensagemList extends ArrayAdapter<Mensagem> {

    private Activity context;
    private List<Mensagem> mensagemList;

    public MensagemList(Activity context, List<Mensagem> mensagemList) {
        super(context, R.layout.list_layout, mensagemList);
        this.context = context;
        this.mensagemList = mensagemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewTitulo = (TextView) listViewItem.findViewById(R.id.textViewTitulo);
        TextView textViewCorpo = (TextView) listViewItem.findViewById(R.id.textViewCorpo);
        Mensagem mensagem = mensagemList.get(position);
        textViewTitulo.setText(mensagem.getTituloMensagem());
        textViewCorpo.setText(mensagem.getCorpoMensagem());
        return listViewItem;
    }
}