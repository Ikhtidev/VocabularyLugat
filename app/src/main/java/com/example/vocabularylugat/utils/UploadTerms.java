package com.example.vocabularylugat.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.example.vocabularylugat.model.Term;
import com.example.vocabularylugat.model.Vocabulary;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class UploadTerms {
    ArrayList<Term> terms = new ArrayList<>();
    Term term;
    Context context;

    public UploadTerms(Context context) {
        this.context = context;
    }

    public ArrayList<Term> uploadData() {
        try {
            InputStream myInput;
            AssetManager assetManager = context.getAssets();
            myInput = assetManager.open("Terms.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator<Row> qatorlar = mySheet.rowIterator();
            while (qatorlar.hasNext()) {
                HSSFRow qator = (HSSFRow) qatorlar.next();
                Iterator<Cell> ustunlar = qator.cellIterator();
                int ustun_no = 0;
                term = new Term();
                while (ustunlar.hasNext()) {
                    HSSFCell ustun = (HSSFCell) ustunlar.next();
                    if (ustun_no == 0) {
                        term.setTerm_title(ustun.toString());
                    } else if (ustun_no == 1) {
                        term.setTerm_text(ustun.toString());
                    }
                    ustun_no++;
                }
                terms.add(term);
            }

        } catch (Exception e) {
            Toast.makeText(context, "Ma'lumot yuklanishida xatolik!", Toast.LENGTH_SHORT).show();
        }
        return terms;
    }
}
