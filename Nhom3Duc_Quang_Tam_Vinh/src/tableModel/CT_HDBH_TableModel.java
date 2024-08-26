package tableModel;

import entity.CT_HoaDonBanHang;
import javax.swing.table.AbstractTableModel;

import java.util.List;

public class CT_HDBH_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CT_HoaDonBanHang> ds;
    String[] headers = {"STT", "Tên Thuốc", "Loại thuốc", "Công Dụng", "Thành Phần", "Xuất Xứ", "Số Lượng", "Đơn Vị Tính",
            "Đơn Giá", "Ngày Hết Hạn","Thành Tiền"};

    public CT_HDBH_TableModel(List<CT_HoaDonBanHang> ds) {
        super();
        this.ds = ds;
    }
    public void xoa(int index) {
        ds.remove(index);
        fireTableDataChanged();
    }
    
    
    public void update(List<CT_HoaDonBanHang> ds){
        this.ds = ds;
        fireTableDataChanged();
    }
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CT_HoaDonBanHang ct = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return ct.getThuoc().getTenThuoc();
            case 2:
                return ct.getThuoc().getLoaiThuoc().getTenThuoc();
            case 3:
                return ct.getThuoc().getCongDung();
            case 4:
                return ct.getThuoc().getThanhPhan();
            case 5:
                return ct.getThuoc().getXuatXu();
            case 6:
                return ct.getSoLuong();
            case 7:
                return ct.getThuoc().getDonViTinh();
            case 8:
                return ct.getDonGia();
            case 9:
                return ct.getThuoc().getNgayHetHan();
            case 10:
                return ct.getThuoc().getGiaBan() * ct.getSoLuong();
            default:
                return ct;
        }
    }
}