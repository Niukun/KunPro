package com.nk.demo.ml.staff;

import lombok.Data;

import java.util.List;

/**
 * @title GetStaffBaseInfoResponse
 * @Description: todo
 * @Author tianwl
 * @Company 中科美络科技股份有限公司
 * @Email tianwl@izkml.com
 * @Date 2024/9/20 10:20
 */
@Data
public class GetStaffBaseInfoResponse {

    public DataObject data;
    public FileObject file;

    @Data
    public static class DataObject {
        private Integer age;
        private String avatarImage;
        private String birthDate;
        private String companyEmail;
        private String contactAddress;
        private String contactName;
        private String contactNum;
        private String createdAt;
        private String createdBy;
        private String fertilityStatus;
        private String fertilityStatusName;
        private String formerName;
        private String gender;
        private String genderName;
        private String healthStateCode;
        private Integer height;
        private String hobby;
        private String hometown;
        private String id;
        private String idCertificateNo;
        private String idCertificateType;
        private String idCertificateTypeName;
        private String living;
        private String maritalStatusCode;
        private String maritalStatusName;
        private String nationality;
        private String particInWorkTime;
        private String personalEmail;
        private String personalStatement;
        private String phone;
        private String politicalAffiliationCode;
        private String politicalAffiliationName;
        private String registeredAddress;
        private String registeredType;
        private String registeredTypeName;
        private String relation;
        private String relationName;
        private String serviceLength;
        private String specialty;
        private String staffName;
        private List<UfStaffBaseInfoEduResponse> ufStaffBaseInfoEduResponseList;
        private List<UfStaffBaseInfoFamilyResponse> ufStaffBaseInfoFamilyResponseList;
        private List<UfStaffBaseInfoFileResponse> ufStaffBaseInfoFileResponseList;
        private List<UfStaffBaseInfoLangResponse> ufStaffBaseInfoLangResponseList;
        private List<UfStaffBaseInfoSkillResponse> ufStaffBaseInfoSkillResponseList;
        private List<UfStaffBaseInfoTagResponse> ufStaffBaseInfoTagResponseList;
        private List<UfStaffBaseInfoTitleResponse> ufStaffBaseInfoTitleResponseList;
        private List<UfStaffBaseInfoTrainResponse> ufStaffBaseInfoTrainResponseList;
        private List<UfStaffBaseInfoWorkExpResponse> ufStaffBaseInfoWorkExpResponseList;
        private String updatedAt;
        private String updatedBy;
        private Integer weight;

        @Data
        public static class UfStaffBaseInfoEduResponse {
            private String degreeCertifiNum;
            private String educationEndTime;
            private String educationStartEndTime;
            private String educationStartTime;
            private String graduatedSchool;
            private String graduatedStatus;
            private String graduationCertifiNum;
            private String graduationMajor;
            private String id;
            private String isHighestDegree;
            private String isHighestLevel;
            private String learningType;
            private String mainid;
            private String schoolClass;
            private String schoolingRecordCode;
            private String schoolingRecordName;
            private Integer sort;
        }

        @Data
        public static class UfStaffBaseInfoFamilyResponse {
            private String createdAt;
            private String createdBy;
            private String dateOfBirth;
            private String employmentOrganization;
            private String id;
            private String mainid;
            private String memberName;
            private String memberPhone;
            private String positionId;
            private String relation;
            private String relationName;
            private String updatedAt;
            private String updatedBy;
        }

        @Data
        public static class UfStaffBaseInfoFileResponse {
            private String certifiDocuName;
            private String certificateAttach;
            private List<CertificateAttach> certificateAttachList;
            private String certificateNum;
            private String createdAt;
            private String createdBy;
            private String expiryDate;
            private String id;
            private String issueDate;
            private String issuingAgency;
            private String mainid;
            private String updatedAt;
            private String updatedBy;
            private String validityStartDate;

            @Data
            public static class CertificateAttach {
                private String id;
                private String name;
                private String size;
                private String uid;
                private String url;
            }
        }

        @Data
        public static class UfStaffBaseInfoLangResponse {
            private String createdAt;
            private String createdBy;
            private String flanguageCode;
            private String flanguageName;
            private String flanguageProficiencyCode;
            private String flanguageProficiencyName;
            private String id;
            private String languageNotes;
            private String mainid;
            private String updatedAt;
            private String updatedBy;

        }

        @Data
        public static class UfStaffBaseInfoSkillResponse {
            private String createdAt;
            private String createdBy;
            private String id;
            private String mainid;
            private String skillMastery;
            private String skillMasteryName;
            private String skillName;
            private String skillNotes;
            private String updatedAt;
            private String updatedBy;

        }

        @Data
        public static class UfStaffBaseInfoTagResponse {
            private String createdAt;
            private String createdBy;
            private String id;
            private String pk;
            private String staffId;
            private String tag;
            private String updatedAt;
            private String updatedBy;

        }

        @Data
        public static class UfStaffBaseInfoTitleResponse {
            private String createdAt;
            private String createdBy;
            private String id;
            private String mainid;
            private String titleAcquTime;
            private String titleCertificateNum;
            private String titleEvaluatAgency;
            private String titleFile;
            private List<TitleFile> titleFileList;
            private String titleLevel;
            private String titleLevelName;
            private String titleName;
            private String titleNotes;
            private String updatedAt;
            private String updatedBy;

            @Data
            public static class TitleFile {
                private String id;
                private String name;
                private String size;
                private String uid;
                private String url;
            }
        }

        @Data
        public static class UfStaffBaseInfoTrainResponse {
            private String createdAt;
            private String createdBy;
            private String id;
            private String mainid;
            private String trainCertifiName;
            private String trainCertifiNum;
            private String trainEndTime;
            private String trainName;
            private String trainNotes;
            private String trainStartEndTime;
            private String trainStartTime;
            private String trainUnit;
            private String updatedAt;
            private String updatedBy;

        }

        @Data
        public static class UfStaffBaseInfoWorkExpResponse {
            private String afterPost;
            private String beginEndTime;
            private String certifier;
            private String certifierAndPhone;
            private String certifierContactNum;
            private String createdAt;
            private String createdBy;
            private String employmentOrganization;
            private String id;
            private String leaveReason;
            private String mainid;
            private String resignationSalary;
            private Integer sort;
            private String updatedAt;
            private String updatedBy;
            private String workEndTime;
            private String workStartEndTime;
            private String workStartTime;

        }
    }

    @Data
    public static class FileObject {
        private String date;
        private String fileName;
        private String jd;
        private String nd;
        private String staffJobNo;
        private String staffName;
    }
}
